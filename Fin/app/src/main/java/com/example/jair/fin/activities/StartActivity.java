package com.example.jair.fin.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jair.fin.R;
import com.example.jair.fin.dao.FinDao;

import static com.example.jair.fin.schema.Schema.SchemaTranByDate.ASSETS;
import static com.example.jair.fin.schema.Schema.SchemaTranByDate.EXPENSES;
import static com.example.jair.fin.schema.Schema.SchemaTranByDate.REMAINING;
import static com.example.jair.fin.schema.Schema.SchemaTranByDate.USER_FK;

public class StartActivity extends AppCompatActivity {

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        username = getIntent().getStringExtra("username");
        Toast.makeText(this, "welcome "+username , Toast.LENGTH_SHORT).show();
    }

    public void nextEvent(View view){

        EditText monthly_incomeV = (EditText) findViewById(R.id.income_input);
        EditText monthly_expensesV = (EditText) findViewById(R.id.expense_input);

        if (String.valueOf(monthly_incomeV.getText()).equals("")){
            Toast.makeText(this, "please set a monthly income", Toast.LENGTH_SHORT).show();
            return;
        }

        if (String.valueOf(monthly_expensesV.getText()).equals("")){
            monthly_expensesV.setText("0");
        }

        double income = Double.valueOf(String.valueOf(monthly_incomeV.getText()));
        double expense = Double.valueOf(String.valueOf(monthly_expensesV.getText()));
        if (income<expense){
            Toast.makeText(this, "your monthly income should be higher \nthan your expenses", Toast.LENGTH_SHORT).show();
            return;
        }

        FinDao finDao = new FinDao(this);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ASSETS,income);
        contentValues.put(EXPENSES,expense);
        contentValues.put(REMAINING,income-expense);
        contentValues.put(USER_FK,finDao.getUserByName(username).getUser_id());
        finDao.insertTOM(contentValues);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
