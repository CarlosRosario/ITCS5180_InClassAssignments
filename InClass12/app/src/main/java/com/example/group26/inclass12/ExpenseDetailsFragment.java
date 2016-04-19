package com.example.group26.inclass12;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseDetailsFragment extends Fragment {

    Expense expense;

    public ExpenseDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_expense_details, container, false);


        TextView name = (TextView) view.findViewById(R.id.name_details);
        TextView category = (TextView) view.findViewById(R.id.category_details);
        TextView amount = (TextView) view.findViewById(R.id.amount_details);
        TextView date = (TextView) view.findViewById(R.id.date_details);

        name.setText(expense.getExpenseName());
        category.setText(expense.getCategory());
        amount.setText(expense.getAmount());
        date.setText(expense.getDate());

        return view;
    }

    public void setExpense(Expense expense){
        this.expense = expense;
    }

}
