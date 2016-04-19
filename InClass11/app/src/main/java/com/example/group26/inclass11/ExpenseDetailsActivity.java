package com.example.group26.inclass11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ExpenseDetailsActivity extends AppCompatActivity {


    Expense expense; // The Expense object that is passed to this activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);

        if(getIntent() != null && getIntent().getExtras() != null){
            expense = (Expense)getIntent().getExtras().getSerializable("SELECTEDEXPENSE");
        }

        TextView name = (TextView) findViewById(R.id.name_details);
        TextView category = (TextView) findViewById(R.id.category_details);
        TextView amount = (TextView) findViewById(R.id.amount_details);
        TextView date = (TextView) findViewById(R.id.date_details);

        name.setText(expense.getExpenseName());
        category.setText(expense.getCategory());
        amount.setText(expense.getAmount());
        date.setText(expense.getDate());
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.activity_expense_details_customactionbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_expense:
                Toast.makeText(ExpenseDetailsActivity.this, "Deleting Expense..", Toast.LENGTH_SHORT).show();
                //TODO: need to delete expense from database
                //THIS WAS MADE OPTIONAL
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}