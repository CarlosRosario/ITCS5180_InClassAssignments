package com.example.group26.inclass12;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddExpenseFragment extends Fragment {

    AddExpenseFragment.AddExpenseFragmentNavigateToExpenseList myListener;
    String email;
    String date;
    Spinner spinner;
    String selectedCategory;
    EditText amountEditText;
    EditText expenseNameEditText;
    EditText dateEditText;
    Calendar myCalendar = Calendar.getInstance();
    final Firebase firebase = new Firebase("https://inclass12-9999.firebaseio.com/");

    public AddExpenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_expense, container, false);






        spinner = (Spinner)view.findViewById(R.id.categorySpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        amountEditText = (EditText)view.findViewById(R.id.amountEditText);
        dateEditText = (EditText)view.findViewById(R.id.dateEditText);
        expenseNameEditText = (EditText)view.findViewById(R.id.phoneBillEditText);

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
                new DatePickerDialog(getActivity(), returnDatePicker, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        view.findViewById(R.id.addExpenseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (amountEditText.getText() == null || amountEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill in amount", Toast.LENGTH_SHORT).show();
                    return;
                } else if (selectedCategory.isEmpty()) {
                    Toast.makeText(getActivity(), "Please select a category", Toast.LENGTH_SHORT).show();
                    return;
                } else if (dateEditText.getText() == null || dateEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter a date", Toast.LENGTH_SHORT).show();
                    return;
                } else if (expenseNameEditText.getText() == null || expenseNameEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter a name for the expense", Toast.LENGTH_SHORT).show();
                    return;
                }

                Expense expense = new Expense();
                expense.setAmount(amountEditText.getText().toString());
                expense.setCategory(selectedCategory);
                expense.setDate(dateEditText.getText().toString());
                expense.setExpenseName(expenseNameEditText.getText().toString());
                expense.setUserEmailId(email);

                Firebase postRef = firebase.child("Expense");
                postRef.push().setValue(expense);

                myListener.navigateToExpenseListFragmentFromAddExpense();
//                Intent resultIntent = getIntent();
//                //resultIntent.putExtra("EXPENSE", expense);
//                setResult(Activity.RESULT_OK, resultIntent);
//
//                finish();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof AddExpenseFragment.AddExpenseFragmentNavigateToExpenseList){
            myListener = (AddExpenseFragment.AddExpenseFragmentNavigateToExpenseList)context;
        }
        else {
            throw new RuntimeException(context.toString());
        }
    }

    public void setEmail(String email){
        this.email = email;
    }

    public interface AddExpenseFragmentNavigateToExpenseList{
        void navigateToExpenseListFragmentFromAddExpense();
    }
}
