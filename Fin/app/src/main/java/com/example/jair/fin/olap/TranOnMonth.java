package com.example.jair.fin.olap;

/**
 * Created by Jair on 2/9/2017.
 */

public class TranOnMonth {

    private long tom_id;
    private double assets;
    private double expenses;
    private double remaining;

    public TranOnMonth(long tom_id, double assets, double expenses, double remaining) {
        this.tom_id = tom_id;
        this.assets = assets;
        this.expenses = expenses;
        this.remaining = remaining;
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
