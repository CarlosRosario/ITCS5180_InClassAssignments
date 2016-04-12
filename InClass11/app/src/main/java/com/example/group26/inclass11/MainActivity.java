package com.example.group26.inclass11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Firebase ref;
    EditText emailEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://luminous-torch-5331.firebaseio.com/");

        emailEditText = (EditText)findViewById(R.id.emailEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);


        // Check if user is authenticated if so - go ahead and go to the ExpensesListActivity
        // Else stay at login activity
        // UNDER CONSTRUCTION
        AuthData authentication = ref.getAuth();
        if(authentication == null){

        }
        else {
            Log.d("auth", authentication.getUid());
            Intent ExpenseListIntent = new Intent(MainActivity.this, ExpensesListActivity.class);
            ExpenseListIntent.putExtra("", "");
            startActivity(ExpenseListIntent);
        }
        // UNDER CONSTRUCTION


        // Create a handler to handle the result of the authentication
        final Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                // Authenticated successfully with payload authData
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // Authenticated failed with error firebaseError
            }
        };

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.authWithPassword(emailEditText.getText().toString(), passwordEditText.getText().toString(),
                        new Firebase.AuthResultHandler() {
                            @Override
                            public void onAuthenticated(AuthData authData) {
                                // Authentication just completed successfully :)
                                Map<String, String> map = new HashMap<String, String>();
                                map.put("provider", authData.getProvider());
                                if(authData.getProviderData().containsKey("displayName")) {
                                    map.put("displayName", authData.getProviderData().get("displayName").toString());
                                }
                                ref.child("users").child(authData.getUid()).setValue(map);

                                Intent ExpenseListActivity = new Intent(MainActivity.this, ExpensesListActivity.class);
                                startActivity(ExpenseListActivity);
                            }
                            @Override
                            public void onAuthenticationError(FirebaseError error) {
                                Toast.makeText(MainActivity.this, "Unsuccessful authentication", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        findViewById(R.id.createAccountButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupActivity = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(signupActivity);
            }
        });

    }


}
