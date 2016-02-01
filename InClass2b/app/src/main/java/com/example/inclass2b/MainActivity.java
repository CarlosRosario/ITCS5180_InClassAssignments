// Assignment # 2b
// MainActivity.java
// Carlos Adrian Rosario

package com.example.inclass2b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Set up radio group
        rg = (RadioGroup)findViewById(R.id.RadioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton)findViewById(checkedId);
            }
        });

        // Set up button click
        Button btn = (Button)findViewById(R.id.convertButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rg.getCheckedRadioButtonId() == R.id.toInchesRadioButton){
                    EditText e = (EditText)findViewById(R.id.inputEditText);
                    String input = e.getText().toString();

                    if(input != null && !input.isEmpty()){
                        if(isNumeric(input)){
                            double meters = Double.parseDouble(input);
                            double inches = convertToInches(meters);

                            updateDisplay("Inches:", Double.toString(inches));
                        }
                        else {
                            Toast.makeText(MainActivity.this, "input is not a number", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "input is empty", Toast.LENGTH_LONG).show();
                    }
                }
                else if(rg.getCheckedRadioButtonId() == R.id.toFeetsRadioButton){
                    EditText e = (EditText)findViewById(R.id.inputEditText);
                    String input = e.getText().toString();

                    if(input != null && !input.isEmpty()){
                        if(isNumeric(input)){
                            double meters = Double.parseDouble(input);
                            double feet = convertToFeet(meters);

                            updateDisplay("Feet:", Double.toString(feet));
                        }
                        else {
                            Toast.makeText(MainActivity.this, "input is not a number", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "input is empty", Toast.LENGTH_LONG).show();
                    }
                }
                else if(rg.getCheckedRadioButtonId() == R.id.toMilesRadioButton){
                    EditText e = (EditText)findViewById(R.id.inputEditText);
                    String input = e.getText().toString();

                    if(input != null && !input.isEmpty()){
                        if(isNumeric(input)){
                            double meters = Double.parseDouble(input);
                            double miles = convertToMiles(meters);

                            updateDisplay("Miles:", Double.toString(miles));
                        }
                        else {
                            Toast.makeText(MainActivity.this, "input is not a number", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "input is empty", Toast.LENGTH_LONG).show();
                    }
                }
                else if(rg.getCheckedRadioButtonId() == R.id.clearAllRadioButton){
                    resetDisplay();
                }
            }
        });


    }

    public boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public double convertToInches(double meters){
        return meters * 39.3701;
    }

    public double convertToFeet(double meters){
        return meters * 3.28;
    }

    public double convertToMiles(double meters){
        return meters * .0006;
    }

    public void updateDisplay(String result, String conversionResult){
        TextView resultsTextView = (TextView)findViewById(R.id.resultTextView);
        TextView conversionResultsTextView = (TextView)findViewById(R.id.conversionResultsTextView);

        resultsTextView.setText(result);
        conversionResultsTextView.setText(conversionResult);
    }

    public void resetDisplay(){
        TextView resultsTextView = (TextView)findViewById(R.id.resultTextView);
        EditText inputBox = (EditText)findViewById(R.id.inputEditText);
        TextView conversionResultsTextView = (TextView)findViewById(R.id.conversionResultsTextView);

        resultsTextView.setText("Result:");
        conversionResultsTextView.setText("");
        inputBox.setText("");
    }
}
