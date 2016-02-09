package com.group26.passwordgenerator;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    String passwordLength;
    int length;
    boolean num;
    boolean upper;
    boolean lower;
    boolean special;

    CheckBox numberCheckBox;
    CheckBox upperCheckBox;
    CheckBox lowerCheckBox;
    CheckBox specialCheckBox;

    TextView passwordTextView;

    private Handler handler;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressDialog = new ProgressDialog(MainActivity.this);

        // Get handles user controls
        spinner = (Spinner)findViewById(R.id.spinner);
        numberCheckBox = (CheckBox)findViewById(R.id.numberCheckBox);
        upperCheckBox = (CheckBox)findViewById(R.id.upperCaseCheckBox);
        lowerCheckBox = (CheckBox)findViewById(R.id.lowerCaseCheckBox);
        specialCheckBox = (CheckBox)findViewById(R.id.specialCheckBox);
        passwordTextView = (TextView)findViewById(R.id.passwordTextView);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.getData() != null && msg.getData().containsKey("PASSWORD")){
                    String password = msg.getData().getString("PASSWORD");
                    passwordTextView.setText(password);
                    progressDialog.dismiss();


                }
                else{
                    Toast.makeText(MainActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
                }

                return true;

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                passwordLength = (String)spinner.getSelectedItem();

                if(passwordLength.equals("8 - 12")){
                    length = 0;
                }
                else if(passwordLength.equals("13 - 17")){
                    length = 1;
                }
                else if(passwordLength.equals("18 - 22")){
                    length = 2;
                }
//                else if(passwordLength.toLowerCase().equals("select password length")){
//                    Toast.makeText(MainActivity.this, "Please select a valid password length range", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        findViewById(R.id.asyncButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                num = numberCheckBox.isChecked();
                upper = upperCheckBox.isChecked();
                lower = lowerCheckBox.isChecked();
                special = specialCheckBox.isChecked();

                if(num == false && upper == false && lower == false && special == false){
                    Toast.makeText(MainActivity.this, "Please select at least one checkbox", Toast.LENGTH_SHORT).show();
                }
                else {
                    new MyUtil().execute();
                }


            }
        });

        findViewById(R.id.threadButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                num = numberCheckBox.isChecked();
                upper = upperCheckBox.isChecked();
                lower = lowerCheckBox.isChecked();
                special = specialCheckBox.isChecked();

                if(num == false && upper == false && lower == false && special == false){
                    Toast.makeText(MainActivity.this, "Please select at least one checkbox", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog.setCancelable(false);
                    progressDialog.setTitle("Generating Password");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();

                    new Thread(new MyUtilRunner()).start();
                }
            }
        });
    }

    private class MyUtil extends AsyncTask<Void, Void, String>{

        ProgressDialog progressDialogAsync;
        @Override
        protected void onPreExecute() {
            progressDialogAsync = new ProgressDialog(MainActivity.this);
            progressDialogAsync.setCancelable(false);
            progressDialogAsync.setTitle("Generating Password");
            progressDialogAsync.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialogAsync.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String password = Util.getPassword(length, num, upper, lower, special);
            return password;
        }

        @Override
        protected void onPostExecute(String password){
            passwordTextView.setText(password);
            progressDialogAsync.dismiss();
        }
    }

    public class MyUtilRunner implements Runnable {

        private void sendMessage(String messageText){
            Bundle bundle = new Bundle();
            bundle.putString("PASSWORD", messageText);

            Message message = new Message();
            message.setData(bundle);
            handler.sendMessage(message);
        }

        @Override
        public void run() {
            String password = Util.getPassword(length, num, upper, lower,special);
            sendMessage(password);
        }
    }
}
