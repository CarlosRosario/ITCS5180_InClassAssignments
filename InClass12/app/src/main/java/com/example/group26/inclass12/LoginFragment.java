package com.example.group26.inclass12;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    final Firebase firebase = new Firebase("https://inclass12-9999.firebaseio.com/");
    OnFragmentInteractionListener myListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener){
            myListener = (OnFragmentInteractionListener)context;
        }
        else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionLIstener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Check if there is a current user session
        boolean isThereACurrentUserSession = isCurrentUserAuthenticated(firebase);
        if(isThereACurrentUserSession){
            // Start Conversation activity, and finish the login activity
            myListener.navigateToExpenseListFragment();

        }


        final EditText emailEditText = (EditText)view.findViewById(R.id.emailEditText);
        final EditText passwordEditText = (EditText)view.findViewById(R.id.passwordEditText);

//        // Login Button logic
//        //noinspection ConstantConditions
        view.findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebase.authWithPassword(emailEditText.getText().toString(), passwordEditText.getText().toString(),
                        new Firebase.AuthResultHandler() {
                            @Override
                            public void onAuthenticated(AuthData authData) {
                                // Authentication just completed successfully :)
                                //Map<String, String> map = new HashMap<String, String>();
                                //map.put("eprovider", authData.getProvider());
                                //if(authData.getProviderData().containsKey("displayName")) {
                                //    map.put("displayName", authData.getProviderData().get("displayName").toString());
                                //}
                                //firebase.child("users").child(authData.getUid()).setValue(map);
                                myListener.navigateToExpenseListFragment();
                            }

                            @Override
                            public void onAuthenticationError(FirebaseError error) {
                                Toast.makeText(getActivity(), "Unsuccessful authentication", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
//
//
//        // Create New Account Button logic
//        //noinspection ConstantConditions
        view.findViewById(R.id.createAccountButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.navigateToSignUpFragment();
            }
        });
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public boolean isCurrentUserAuthenticated(Firebase firebase){

        AuthData authData = firebase.getAuth();

        if(authData != null){
            return true;
        }
        else {
            return false;
        }
    }


    public interface OnFragmentInteractionListener {
        void navigateToExpenseListFragment();
        void navigateToSignUpFragment();
    }
}
