package com.example.group26.inclass12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener, SignUpFragment.OnFragmentInteractionListener
, ExpenseListFragment.ExpenseListFragmentNavigator, AddExpenseFragment.AddExpenseFragmentNavigateToExpenseList{
    @Override
    public void navigateToExpenseListFragmentFromAddExpense() {
        getFragmentManager().beginTransaction().replace(R.id.container, new ExpenseListFragment(), "expenselist").commit();
    }

    //https://inclass12-9999.firebaseio.com/
    Firebase ref;
    String email;

    @Override
    public void navigateToSignUpFragment() {
        getFragmentManager().beginTransaction().replace(R.id.container, new SignUpFragment(), "signup").commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(MainActivity.this);
        ref = new Firebase("https://inclass12-9999.firebaseio.com/");

        getFragmentManager().beginTransaction().add(R.id.container, new LoginFragment(), "login").commit();

    }

    @Override
    public void navigateToExpenseListFragment() {
        getFragmentManager().beginTransaction().replace(R.id.container, new ExpenseListFragment(), "expenselist").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.custom_action_bar, menu);
        return true;
    }

    public void addExpense(MenuItem item) {

        Firebase emailRef = new Firebase("https://inclass12-9999.firebaseio.com/users/username/email");

        emailRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                email = snapshot.getValue().toString();
                Log.d("email", email);

                AddExpenseFragment f = new AddExpenseFragment();
                f.setEmail(email);
                getFragmentManager().beginTransaction().replace(R.id.container, f, "addexpense").commit();
//                Intent addExpenseActivity = new Intent(ExpensesListActivity.this, AddExpenseActivity.class);
//                addExpenseActivity.putExtra("EMAIL", email);
//                startActivityForResult(addExpenseActivity, 100);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

        public void logout(MenuItem item){
            ref.unauth();
            getFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment(), "login").commit();
        }


    @Override
    public void navigateToLoginFragment() {
        getFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment(), "login").commit();
    }

    @Override
    public void navigateToExpenseDetailsActivity(Expense selectedExpense) {
        ExpenseDetailsFragment f = new ExpenseDetailsFragment();
        f.setExpense(selectedExpense);
        getFragmentManager().beginTransaction().replace(R.id.container, f, "expensedetails").addToBackStack(null).commit();

    }


    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }

    }
}
