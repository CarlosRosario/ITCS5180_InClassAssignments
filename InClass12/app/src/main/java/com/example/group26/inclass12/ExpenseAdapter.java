package com.example.group26.inclass12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Carlos on 4/11/2016.
 */
public class ExpenseAdapter extends ArrayAdapter<Expense> {

    List<Expense> mData;
    Context mContext;
    int mResource;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }

        Expense expense = mData.get(position);

        // Set venue name
        TextView venueNameTextView = (TextView)convertView.findViewById(R.id.categoryNameCustomTextView);
        venueNameTextView.setText(expense.getExpenseName());

        // Set category name
        TextView categoryNameTextView = (TextView)convertView.findViewById(R.id.dollarAmountCustomTextView);
        categoryNameTextView.setText("$" + expense.getAmount());


        return convertView;

    }

    public ExpenseAdapter(Context context, int resource, List<Expense> objects){
        super(context, resource, objects);
        this.mData = objects;
        this.mContext = context;
        this.mResource = resource;
    }
}