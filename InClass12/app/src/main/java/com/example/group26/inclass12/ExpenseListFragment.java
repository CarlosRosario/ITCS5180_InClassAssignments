package com.example.group26.inclass12;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseListFragment extends Fragment {

    ExpenseListFragmentNavigator myListener;
    final Firebase firebase = new Firebase("https://inclass12-9999.firebaseio.com/");
    String email;
    List<Expense> expenseList = new ArrayList<Expense>();
    ListView listView;
    ExpenseAdapter expenseAdapter;

    public ExpenseListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_expense_list, container, false);



        firebase.child("users").child("username").child("email").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                email = snapshot.getValue().toString();
                Log.d("test2", email);


                Firebase ref = new Firebase("https://inclass12-9999.firebaseio.com/Expense");
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


        Firebase ref = new Firebase("https://inclass12-9999.firebaseio.com/");

        expenseAdapter = new ExpenseAdapter(getActivity(), R.layout.row_item_layout_custom, expenseList);
        listView = (ListView)view.findViewById(R.id.expenseListView);
        listView.setAdapter(expenseAdapter);
        expenseAdapter.setNotifyOnChange(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Expense selectedExpense = expenseList.get(position);
                myListener.navigateToExpenseDetailsActivity(selectedExpense); // should be "detailsFragment" code hiccup.
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ExpenseListFragmentNavigator){
            myListener = (ExpenseListFragmentNavigator)context;
        }
        else {
            throw new RuntimeException(context.toString());
        }
    }

    public interface ExpenseListFragmentNavigator {
        void navigateToExpenseDetailsActivity(Expense selectedExpense);
    }

}
