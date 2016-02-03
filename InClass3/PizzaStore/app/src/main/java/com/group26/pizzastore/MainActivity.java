// InClass 3
// MainActivity.java
// Carlos Rosario, Preeti Harkanth, Meredith Browne

package com.group26.pizzastore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected static final String ORDER_KEY = "ORDER";

    CharSequence[] items = {"Bacon", "Cheese", "Garlic", "Green Pepper", "Mushroom", "Olives", "Onions", "Red Pepper"};
    List<String> addedToppings = new ArrayList<String>();

    // References to our layouts
    LinearLayout layout;
    LinearLayout layout2;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (LinearLayout) findViewById(R.id.linearLayout);
        layout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        progressBar = (ProgressBar) findViewById(R.id.numberToppingsProgressBar);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a Topping")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ImageView image;
                        LinearLayout finalLayout;

                        if(addedToppings.size() > 9){
                            Toast.makeText(MainActivity.this, "Cannot add more than 10 toppings", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            if(addedToppings.size() >= 5){
                                finalLayout = layout2;
                            }
                            else {
                                finalLayout = layout;
                            }
                            switch (which) {
                                case 0:
                                    image = new ImageView(MainActivity.this);
                                    image.setImageResource(R.mipmap.bacon);
                                    finalLayout.addView(image);
                                    addedToppings.add("bacon");
                                    progressBar.setProgress(progressBar.getProgress() + 1);
                                    break;

                                case 1:
                                    image = new ImageView(MainActivity.this);
                                    image.setImageResource(R.mipmap.cheese);
                                    finalLayout.addView(image);
                                    addedToppings.add("cheese");
                                    progressBar.setProgress(progressBar.getProgress() + 1);
                                    break;

                                case 2:
                                    image = new ImageView(MainActivity.this);
                                    image.setImageResource(R.mipmap.garlic);
                                    finalLayout.addView(image);
                                    addedToppings.add("garlic");
                                    progressBar.setProgress(progressBar.getProgress() + 1);
                                    break;
                                case 3:
                                    image = new ImageView(MainActivity.this);
                                    image.setImageResource(R.mipmap.green_pepper);
                                    finalLayout.addView(image);
                                    addedToppings.add("green_pepper");
                                    progressBar.setProgress(progressBar.getProgress() + 1);
                                    break;
                                case 4:
                                    image = new ImageView(MainActivity.this);
                                    image.setImageResource(R.mipmap.mushroom);
                                    finalLayout.addView(image);
                                    addedToppings.add("mushroom");
                                    progressBar.setProgress(progressBar.getProgress() + 1);
                                    break;
                                case 5:
                                    image = new ImageView(MainActivity.this);
                                    image.setImageResource(R.mipmap.olives);
                                    finalLayout.addView(image);
                                    addedToppings.add("olives");
                                    progressBar.setProgress(progressBar.getProgress() + 1);
                                    break;
                                case 6:
                                    image = new ImageView(MainActivity.this);
                                    image.setImageResource(R.mipmap.onion);
                                    finalLayout.addView(image);
                                    addedToppings.add(("onion"));
                                    progressBar.setProgress(progressBar.getProgress() + 1);
                                    break;
                                case 7:
                                    image = new ImageView(MainActivity.this);
                                    image.setImageResource(R.mipmap.red_pepper);
                                    finalLayout.addView(image);
                                    addedToppings.add("red_pepper");
                                    progressBar.setProgress(progressBar.getProgress() + 1);
                                    break;
                            }
                        }



                    }
                });

        final AlertDialog alert = builder.create();
        findViewById(R.id.toppingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert.show();

            }
        });

        findViewById(R.id.checkoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double price;
                double deliveryCost = 0;

                price = 6.5 + (1.5 * addedToppings.size());
                CheckBox deliverCheckBox = (CheckBox)findViewById(R.id.deliverCheckBox);

                // If the deliver check box is checked lets go ahead and add 4 to the final price.
                if(deliverCheckBox != null && deliverCheckBox.isChecked()){
                    price = price + 4;
                    deliveryCost = 4.0;
                }

                Order order = new Order(6.5, deliveryCost, price, deliverCheckBox.isChecked(), addedToppings.size(), 1.5, addedToppings);
                Intent intent = new Intent(getBaseContext(), OrderActivity.class);
                intent.putExtra(ORDER_KEY, order);
                startActivity(intent);
            }
        });

        findViewById(R.id.clearPizzaButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(0);
                layout.removeAllViewsInLayout();
                layout2.removeAllViewsInLayout();
                CheckBox deliverCheckBox = (CheckBox)findViewById(R.id.deliverCheckBox);
                deliverCheckBox.setChecked(false);
            }
        });
    }
}
