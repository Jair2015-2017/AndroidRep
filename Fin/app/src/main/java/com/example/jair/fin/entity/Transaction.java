package com.example.jair.fin.entity;

import android.widget.TextView;

/**
 * Created by Jair on 2/9/2017.
 */

public class Transaction {
    private long tran_id;
    private String tran_name;
    private double tran_amount;
    private String tran_type;
    private String tran_date;
    private User user;
    private Category category;

    public Transaction(){

    }

    public Transaction(long tran_id, String tran_name, double tran_amount, String tran_type, String tran_date, User user, Category category) {
        this.tran_id = tran_id;
        this.tran_name = tran_name;
        this.tran_amount = tran_amount;
        this.tran_type = tran_type;
        this.tran_date = tran_date;
        this.user = user;
        this.category = category;
    }

    public long getTran_id() {
        return tran_id;
    }

    public void setTran_id(long tran_id) {
        this.tran_id = tran_id;
    }

    public String getTran_name() {
        return tran_name;
    }

    public void setTran_name(String tran_name) {
        this.tran_name = tran_name;
    }

    public double getTran_amount() {
        return tran_amount;
    }

    public void setTran_amount(double tran_amount) {
        this.tran_amount = tran_amount;
    }

    public String getTran_type() {
        return tran_type;
    }

    public void setTran_type(String tran_type) {
        this.tran_type = tran_type;
    }

    public String getTran_date() {
        return tran_date;
    }

    public void setTran_date(String tran_date) {
        this.tran_date = tran_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
