package com.example.jair.fin.dto;

/**
 * Created by Jair on 2/4/2017.
 */

public class User {

    public User(){

    }

    public User(long user_id, String username, String password, String email, String name_user, String surname, String created, String deleted) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name_user = name_user;
        this.surname = surname;
        this.created = created;
        this.deleted = deleted;
    }

    private long user_id;
    private String username;
    private String password;
    private String email;
    private String name_user;
    private String surname;
    private String created;
    private String deleted;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}
