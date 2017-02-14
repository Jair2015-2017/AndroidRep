package com.example.jair.fin.dto.olap;

/**
 * Created by Jair on 2/9/2017.
 */

public class TranOnMonth {

    private long tom_id;
    private double assets;
    private double expenses;
    private double remaining;
    private long user_fk;

    public TranOnMonth(long tom_id, double assets, double expenses, double remaining, long user_fk) {
        this.tom_id = tom_id;
        this.assets = assets;
        this.expenses = expenses;
        this.remaining = remaining;
        this.user_fk = user_fk;
    }

    public long getUser_fk() {
        return user_fk;
    }

    public void setUser_fk(long user_fk) {
        this.user_fk = user_fk;
    }


    public long getTom_id() {
        return tom_id;
    }

    public void setTom_id(long tom_id) {
        this.tom_id = tom_id;
    }

    public double getAssets() {
        return assets;
    }

    public void setAssets(double assets) {
        this.assets = assets;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getRemaining() {
        return remaining;
    }

    public void setRemaining(double remaining) {
        this.remaining = remaining;
    }
}
