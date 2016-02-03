// InClass 3
// MainActivity.java
// Carlos Rosario, Preeti Harkanth, Meredith Browne

package com.group26.pizzastore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Order order = null;
        String allToppings = "";

        if(getIntent().getExtras() != null){
            order = (Order)getIntent().getExtras().getSerializable(MainActivity.ORDER_KEY);
            TextView basePriceTextView = (TextView)findViewById(R.id.base_price);
            basePriceTextView.setText(String.valueOf(order.getBase_price() + "$"));

            TextView toppingsTextView = (TextView)findViewById(R.id.toppings_cost);
            toppingsTextView.setText(String.valueOf(order.getTopping_price() + "$"));

            TextView toppingsListTextView = (TextView)findViewById(R.id.toppings_list);
            for(String s: order.getAddedToppings()){

                //toppingsListTextView.setText(s + ",");
                allToppings += s + ",";
            }
            toppingsListTextView.setText(allToppings);
            //toppingsListTextView.setText(toppingsListTextView.getText().toString().substring(toppingsListTextView.getText().length() - 1));

            TextView deliveryCostTextView = (TextView)findViewById(R.id.delivery_cost);
            deliveryCostTextView.setText(String.valueOf(order.getDelivery_cost()) + "$");

            TextView totalCostTextView = (TextView)findViewById(R.id.total_price);
            totalCostTextView.setText(String.valueOf(order.getTotal_price()+"$"));
        }

        findViewById(R.id.finishButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
