package com.example.jair.fin.entity.olap;

/**
 * Created by Jair on 2/9/2017.
 */

public class Rapport {

    private long rap_id;
    private String cat_name;
    private int day;
    private int week;
    private int month;
    private int year;
    private double amount;

    public Rapport(long rap_id, String cat_name, int day, int week, int month, int year, double amount) {
        this.rap_id = rap_id;
        this.cat_name = cat_name;
        this.day = day;
        this.week = week;
        this.month = month;
        this.year = year;
        this.amount = amount;
    }

    public long getRap_id() {
        return rap_id;
    }

    public void setRap_id(long rap_id) {
        this.rap_id = rap_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
