package com.example.jair.fin.entity;

/**
 * Created by Jair on 2/9/2017.
 */

public class Category {

    private long cat_id;
    private String cat_name;
    private String cat_description;

    public Category(){

    }
    public Category(long cat_id, String cat_name, String cat_description) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.cat_description = cat_description;
    }

    public long getCat_id() {
        return cat_id;
    }

    public void setCat_id(long cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_description() {
        return cat_description;
    }

    public void setCat_description(String cat_description) {
        this.cat_description = cat_description;
    }
}
