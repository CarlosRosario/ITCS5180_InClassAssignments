package com.group26.pizzastore;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    public double getBase_price() {
        return base_price;
    }

    public void setBase_price(double base_price) {
        this.base_price = base_price;
    }

    public double getDelivery_cost() {
        return delivery_cost;
    }

    public void setDelivery_cost(double delivery_cost) {
        this.delivery_cost = delivery_cost;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public boolean isDelivery_checked() {
        return delivery_checked;
    }

    public void setDelivery_checked(boolean delivery_checked) {
        this.delivery_checked = delivery_checked;
    }

    public int getNum_toppings() {
        return num_toppings;
    }

    public void setNum_toppings(int num_toppings) {
        this.num_toppings = num_toppings;
    }

    public double getTopping_price() {
        return topping_price;
    }

    public void setTopping_price(double topping_price) {
        this.topping_price = topping_price;
    }

    public List<String> getAddedToppings() {
        return addedToppings;
    }

    public void setAddedToppings(List<String> addedToppings) {
        this.addedToppings = addedToppings;
    }

    double base_price = 6.5;
    double delivery_cost = 4.0;
    double total_price;
    boolean delivery_checked;
    int num_toppings;
    double topping_price = 1.5;
    List<String> addedToppings;

    public Order(double base_price, double delivery_cost, double total_price, boolean delivery_checked, int num_toppings, double topping_price, List<String> addedToppings) {
        this.base_price = base_price;
        this.delivery_cost = delivery_cost;
        this.total_price = total_price;
        this.delivery_checked = delivery_checked;
        this.num_toppings = num_toppings;
        this.topping_price = topping_price;
        this.addedToppings = addedToppings;
    }
}