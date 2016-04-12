package com.example.group26.inclass11;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    Firebase ref;

    EditText fullNameEditText;
    EditText emailEditText;
    EditText passWordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://luminous-torch-5331.firebaseio.com/");

        fullNameEditText = (EditText)findViewById(R.id.fullNameEditText);
        emailEditText = (EditText)findViewById(R.id.signUpEmailEditText);
        passWordEditText = (EditText)findViewById(R.id.signupPasswordEditText);

        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.signupButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String fullName = fullNameEditText.getText().toString();
                final String signUpEmail = emailEditText.getText().toString();
                final String password = passWordEditText.getText().toString();

                ref.createUser(signUpEmail, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        // if account does not already exist, store the new acct information and display a toast
                        Firebase newUserRef = ref.child("users").child("username");
                        User newUser = new User();

                        newUser.email = signUpEmail;
                        newUser.fullName = fullName;
                        newUser.password = password;

                        newUserRef.setValue(newUser);

                        Toast.makeText(SignupActivity.this, "Successfully created new account", Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {

                        int errorCode = firebaseError.getCode();

                        switch (errorCode) {

                            case FirebaseError.EMAIL_TAKEN:
                                Toast.makeText(SignupActivity.this, "Email is already taken", Toast.LENGTH_SHORT).show();
                                break;

                            default:
                                Toast.makeText(SignupActivity.this, firebaseError.toString(), Toast.LENGTH_SHORT).show();
                                break;

                        }
                    }
                });




                // keep here for now until we figure out how to query the database

                // check if email already exists.



                            }
        });

    }


}
