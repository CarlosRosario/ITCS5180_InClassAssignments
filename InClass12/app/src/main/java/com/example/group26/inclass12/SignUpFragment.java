package com.example.group26.inclass12;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    final Firebase firebase = new Firebase("https://inclass12-9999.firebaseio.com/");
    SignUpFragment.OnFragmentInteractionListener myListener;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof SignUpFragment.OnFragmentInteractionListener){
            myListener = (SignUpFragment.OnFragmentInteractionListener)context;
        }
        else {
            throw new RuntimeException(context.toString() + " must implement Signup.OnfragmentationListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up, container, false);

        final EditText fullNameEditText = (EditText)view.findViewById(R.id.fullNameEditText);
        final EditText emailEditText = (EditText)view.findViewById(R.id.signUpEmailEditText);
        final EditText passWordEditText = (EditText)view.findViewById(R.id.signupPasswordEditText);

        view.findViewById(R.id.signupButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String fullName = fullNameEditText.getText().toString();
                final String signUpEmail = emailEditText.getText().toString();
                final String password = passWordEditText.getText().toString();

                firebase.createUser(signUpEmail, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        // if account does not already exist, store the new acct information and display a toast
                        Firebase newUserRef = firebase.child("users").child("username");
                        User newUser = new User();

                        newUser.email = signUpEmail;
                        newUser.fullName = fullName;
                        newUser.password = password;

                        newUserRef.setValue(newUser);

                        Toast.makeText(getActivity(), "Successfully created new account", Toast.LENGTH_LONG).show();
                        myListener.navigateToLoginFragment();
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {

                        int errorCode = firebaseError.getCode();

                        switch (errorCode) {

                            case FirebaseError.EMAIL_TAKEN:
                                Toast.makeText(getActivity(), "Email is already taken", Toast.LENGTH_SHORT).show();
                                break;

                            default:
                                Toast.makeText(getActivity(), firebaseError.toString(), Toast.LENGTH_SHORT).show();
                                break;

                        }
                    }
                });




                // keep here for now until we figure out how to query the database

                // check if email already exists.



            }
        });

        return view;
    }

    public interface OnFragmentInteractionListener {
        void navigateToLoginFragment();
    }

}
