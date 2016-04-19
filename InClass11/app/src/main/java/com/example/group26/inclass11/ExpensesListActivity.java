package com.example.group26.inclass11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExpensesListActivity extends AppCompatActivity {

    String email;
    List<Expense> expenseList = new ArrayList<Expense>();
    ListView listView;
    ExpenseAdapter expenseAdapter;

    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_list);
        Firebase.setAndroidContext(this);

        Firebase emailRef = new Firebase("https://luminous-torch-5331.firebaseio.com/users/username/email");

        emailRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                email = snapshot.getValue().toString();
                Log.d("email", email);


                ref = new Firebase("https://luminous-torch-5331.firebaseio.com/Expense");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        Log.d("test", "initial call made");
                        //expenseList.clear();
                        expenseAdapter.clear();
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            Expense expense = postSnapshot.getValue(Expense.class);
                            Log.d("test", expense.getUserEmailId());
                            //Log.d("test", email);
                            //expenseList.add(expense);
                            if (expense.getUserEmailId().equals(email)) {
                                Log.d("test", "adding expense to adapter");
                                Log.d("test", expense.getExpenseName());
                                expenseAdapter.add(expense);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Log.d("err", "The read failed: " + firebaseError.getMessage());
                    }
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });


        ref = new Firebase("https://luminous-torch-5331.firebaseio.com/");

        expenseAdapter = new ExpenseAdapter(ExpensesListActivity.this, R.layout.row_item_layout_custom, expenseList);
        listView = (ListView)findViewById(R.id.expenseListView);
        listView.setAdapter(expenseAdapter);
        expenseAdapter.setNotifyOnChange(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Expense selectedExpense = expenseList.get(position);

                Intent ExpenseDetailsIntent = new Intent(ExpensesListActivity.this, ExpenseDetailsActivity.class);
                ExpenseDetailsIntent.putExtra("SELECTEDEXPENSE", selectedExpense);
                startActivity(ExpenseDetailsIntent);

            }
        });

//        ref = new Firebase("https://luminous-torch-5331.firebaseio.com/Expense");
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                Log.d("test", "initial call made");
//                //expenseList.clear();
//                expenseAdapter.clear();
//                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                    Expense expense = postSnapshot.getValue(Expense.class);
//                    Log.d("test", expense.getUserEmailId());
//                    //Log.d("test", email);
//                    //expenseList.add(expense);
//                    if(expense.getUserEmailId().equals(email)){
//                        Log.d("test", "adding expense to adapter");
//                        Log.d("test", expense.getExpenseName());
//                        expenseAdapter.add(expense);
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                Log.d("err", "The read failed: " + firebaseError.getMessage());
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.expenselist_custom_actionbar, menu);
        return true;
    }


    public void addExpense(MenuItem item){

        Firebase emailRef = new Firebase("https://luminous-torch-5331.firebaseio.com/users/username/email");

        emailRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                email = snapshot.getValue().toString();
                Log.d("email", email);

                Intent addExpenseActivity = new Intent(ExpensesListActivity.this, AddExpenseActivity.class);
                addExpenseActivity.putExtra("EMAIL", email);
                startActivityForResult(addExpenseActivity, 100);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });







    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
//            if(data!= null){
//                Expense expense = (Expense)data.getSerializableExtra("EXPENSE");
//                expenseList.add(expense);
//            }
            // Get a reference to our posts
            ref = new Firebase("https://luminous-torch-5331.firebaseio.com/Expense");
            // Attach an listener to read the data at our posts reference
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Log.d("test", "got here");
                    //expenseList.clear();
                    expenseAdapter.clear();
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                        Expense expense = postSnapshot.getValue(Expense.class);

                        //expenseList.add(expense);
                        if(expense.getUserEmailId().equals(email)){
                            expenseAdapter.add(expense);
                        }
                    }
                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });
        }
    }

    public void logout(MenuItem item){
        ref.unauth();
        finish();
    }
}

