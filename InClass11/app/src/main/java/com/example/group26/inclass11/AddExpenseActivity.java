package com.example.group26.inclass11;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddExpenseActivity extends AppCompatActivity {

    String email;
    String date;
    Spinner spinner;
    String selectedCategory;
    EditText amountEditText;
    EditText expenseNameEditText;
    EditText dateEditText;
    Calendar myCalendar = Calendar.getInstance();

    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        ref = new Firebase("https://luminous-torch-5331.firebaseio.com/");


        if(getIntent().getExtras()!= null){
            email = getIntent().getExtras().getString("EMAIL");
        }

        spinner = (Spinner)findViewById(R.id.categorySpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        amountEditText = (EditText)findViewById(R.id.amountEditText);
        dateEditText = (EditText)findViewById(R.id.dateEditText);
        expenseNameEditText = (EditText)findViewById(R.id.phoneBillEditText);

        final DatePickerDialog.OnDateSetListener returnDatePicker = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                dateEditText.setText(sdf.format(myCalendar.getTime()));
            }
        };

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddExpenseActivity.this, returnDatePicker, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        findViewById(R.id.addExpenseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (amountEditText.getText() == null || amountEditText.getText().toString().isEmpty()) {
                    Toast.makeText(AddExpenseActivity.this, "Please fill in amount", Toast.LENGTH_SHORT).show();
                    return;
                } else if (selectedCategory.isEmpty()) {
                    Toast.makeText(AddExpenseActivity.this, "Please select a category", Toast.LENGTH_SHORT).show();
                    return;
                } else if (dateEditText.getText() == null || dateEditText.getText().toString().isEmpty()) {
                    Toast.makeText(AddExpenseActivity.this, "Please enter a date", Toast.LENGTH_SHORT).show();
                    return;
                } else if (expenseNameEditText.getText() == null || expenseNameEditText.getText().toString().isEmpty()) {
                    Toast.makeText(AddExpenseActivity.this, "Please enter a name for the expense", Toast.LENGTH_SHORT).show();
                    return;
                }

                Expense expense = new Expense();
                expense.setAmount(amountEditText.getText().toString());
                expense.setCategory(selectedCategory);
                expense.setDate(dateEditText.getText().toString());
                expense.setExpenseName(expenseNameEditText.getText().toString());
                expense.setUserEmailId(email);

                Firebase postRef = ref.child("Expense");
                postRef.push().setValue(expense);

                Intent resultIntent = getIntent();
                //resultIntent.putExtra("EXPENSE", expense);
                setResult(Activity.RESULT_OK, resultIntent);

                finish();


            }
        });
    }


}
