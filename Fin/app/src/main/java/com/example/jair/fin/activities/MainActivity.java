package com.example.jair.fin.activities;




import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.jair.fin.dao.FinDao;
import com.example.jair.fin.entity.Category;
import com.example.jair.fin.entity.Transaction;
import com.example.jair.fin.entity.User;
import com.example.jair.fin.entity.olap.TranOnMonth;
import com.example.jair.fin.fragments.Home.AddEarningDialog;
import com.example.jair.fin.fragments.account.AccountFragment;
import com.example.jair.fin.fragments.BudgetFragment;
import com.example.jair.fin.fragments.Home.HomeFragment;
import com.example.jair.fin.R;
import com.example.jair.fin.fragments.RapportFragment;
import com.example.jair.fin.fragments.SettingsFragment;
import com.example.jair.fin.fragments.Home.AddSpendingDialog;

import java.util.Calendar;

import static com.example.jair.fin.schema.Schema.SchemaTransaction.CAT_FK;
import static com.example.jair.fin.schema.Schema.SchemaTransaction.TRAN_AMOUNT;
import static com.example.jair.fin.schema.Schema.SchemaTransaction.TRAN_DATE;
import static com.example.jair.fin.schema.Schema.SchemaTransaction.TRAN_NAME;
import static com.example.jair.fin.schema.Schema.SchemaTransaction.TRAN_TYPE;
import static com.example.jair.fin.schema.Schema.SchemaTransaction.USER_FK;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;//hamburger icon
    FragmentTransaction fragmentTransaction;//voor het navigeren between fragmnets
    NavigationView navigationView;//de view voor de drawer menu
    FinDao finDao;
    public static String safe =null;
    AddSpendingDialog addSpendingDialog;
    AddEarningDialog addEarningDialog;
    Dialog dialog;
    Transaction transaction;
    TranOnMonth tom;
    Category category;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        finDao = new FinDao(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_closed);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        fragmentTransaction  = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container,new HomeFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home");
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected (@NonNull MenuItem item){

    Fragment newfragment = null;
    String title = null;
    switch (item.getItemId()) {
        case R.id.account_drawer_item:
            newfragment = new AccountFragment();
            title = "account";
            break;

        case R.id.budget_drawer_item:
            newfragment = new BudgetFragment();
            title = "budget";
            break;
        case R.id.rapport_drawer_item:
            newfragment = new RapportFragment();
            title = "rapport";
            break;
        case R.id.settings_drawer_item:
            newfragment = new SettingsFragment();
            title = "settings";
            break;
        case R.id.home_drawer_item:
            newfragment = new HomeFragment();

        case R.id.logout_drawer_item:
            startActivity(new Intent(this,LoginActivity.class));
            return true;
    }

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, newfragment);
        fragmentTransaction.commit();
        getSupportActionBar().setTitle(title);
        drawerLayout.closeDrawers();

        return true;
}

    public void addSpendingEvent (View view){

        addSpendingDialog = new AddSpendingDialog();

        addSpendingDialog.show(getSupportFragmentManager(), "addspend_diag");

    }

    public void addExpenseOK(View view){

        dialog = addSpendingDialog.getDialog();
        finDao = new FinDao(this);
        category = addSpendingDialog.getCategory();
        calendar = Calendar.getInstance();
        ContentValues contentValues = new ContentValues();

        EditText tran_nameView = (EditText) dialog.findViewById(R.id.add_expense_name_input);
        EditText tran_amountView = (EditText) dialog.findViewById(R.id.add_expense_amount_input);
        String tran_amountStr = String.valueOf(tran_amountView.getText());
        String tran_name = String.valueOf(tran_nameView.getText());


        if (tran_amountStr.equals("")){
            Toast.makeText(this, "spending value should be greater than 0", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            safe = tran_name;
            addSpendingEvent(view);
            return;
        }

        double tran_amount = Double.valueOf(tran_amountStr);

        tom = finDao.getLastTOM();
        double remaining= tom.getRemaining();
        double outcome = remaining - tran_amount;
        if (outcome<0){

            dialog.dismiss();
            Toast.makeText(this, "your expense cannot be greater than your remaining budget", Toast.LENGTH_SHORT).show();
            safe = tran_name;
            addSpendingEvent(view);


        }else {


            String tran_type = "spending";
            String tran_date = String.valueOf(calendar.getTime());
            User user = finDao.getUserByID(1);

            transaction = new Transaction(0, tran_name, tran_amount, tran_type, tran_date, user, category);

            contentValues.put(TRAN_NAME, tran_name);
            contentValues.put(TRAN_AMOUNT, tran_amount);
            contentValues.put(TRAN_TYPE, tran_type);
            contentValues.put(TRAN_DATE, tran_date);
            contentValues.put(USER_FK, user.getUser_id());
            contentValues.put(CAT_FK, category.getCat_id());
            finDao.insertTran(contentValues, transaction);

            tom = finDao.getLastTOM();
            TextView assetsView = (TextView) findViewById(R.id.starting_value);
            TextView expenseView = (TextView) findViewById(R.id.spenden_value);
            TextView remainingView = (TextView) findViewById(R.id.current_value);

            assetsView.setText(String.valueOf(tom.getAssets()));
            expenseView.setText(String.valueOf(tom.getExpenses()));
            remainingView.setText(String.valueOf(tom.getRemaining()));
            dialog.dismiss();
            Toast.makeText(this, "transaction succesful", Toast.LENGTH_SHORT).show();
            safe = null;
        }

    }

    public void addExpenseCancel(View view){
        addSpendingDialog.getDialog().dismiss();
        Toast.makeText(this, "transaction canceled", Toast.LENGTH_SHORT).show();
    }

    public void addEarningEvent(View view) {

        addEarningDialog = new AddEarningDialog();

        addEarningDialog.show(getSupportFragmentManager(), "add_earning_diag");
    }

    public void addEarningOK(View view){

        Dialog dialog= addEarningDialog.getDialog();

        finDao = new FinDao(this);
        Transaction transaction;
        TranOnMonth tom;

        Calendar calendar= Calendar.getInstance();
        ContentValues contentValues = new ContentValues();

        EditText tran_nameView = (EditText) dialog.findViewById(R.id.add_earning_name_input);
        EditText tran_amountView = (EditText) dialog.findViewById(R.id.add_earning_amount_input);
        String tran_amountStr = String.valueOf(tran_amountView.getText());
        String tran_name = String.valueOf(tran_nameView.getText());


        if (tran_amountStr.equals("")){
            Toast.makeText(this, "earning value should be greater than 0", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            safe = tran_name;
            addEarningEvent(view);
            return;
        }

        double tran_amount = Double.valueOf(tran_amountStr);

        String tran_type = "earning";
        String tran_date = String.valueOf(calendar.getTime());
        User user = finDao.getUserByID(1);
        category = finDao.getCategoryByName("earning");

        transaction = new Transaction(0, tran_name, tran_amount, tran_type, tran_date, user, category);

        contentValues.put(TRAN_NAME, tran_name);
        contentValues.put(TRAN_AMOUNT, tran_amount);
        contentValues.put(TRAN_TYPE, tran_type);
        contentValues.put(TRAN_DATE, tran_date);
        contentValues.put(USER_FK, user.getUser_id());
        contentValues.put(CAT_FK,category.getCat_id());

        finDao.insertTran(contentValues, transaction);

        tom = finDao.getLastTOM();
        TextView assetsView = (TextView) findViewById(R.id.starting_value);
        TextView expenseView = (TextView) findViewById(R.id.spenden_value);
        TextView remainingView = (TextView) findViewById(R.id.current_value);

        assetsView.setText(String.valueOf(tom.getAssets()));
        expenseView.setText(String.valueOf(tom.getExpenses()));
        remainingView.setText(String.valueOf(tom.getRemaining()));
        dialog.dismiss();
        Toast.makeText(this, "transaction succesful", Toast.LENGTH_SHORT).show();
        safe = null;
    }

    public void addEarningCancel(View view){
        addEarningDialog.getDialog().dismiss();
        Toast.makeText(this, "transaction canceled", Toast.LENGTH_SHORT).show();
    }

    public void LogOutEvent(View view){
        startActivity(new Intent(this,LoginActivity.class));
    }

}






