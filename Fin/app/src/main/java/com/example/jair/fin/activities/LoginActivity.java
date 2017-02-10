package com.example.jair.fin.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jair.fin.R;
import com.example.jair.fin.dao.FinDao;
import com.example.jair.fin.entity.User;

public class LoginActivity extends AppCompatActivity {

    String thePassword,theUsername;User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FinDao finDao = new FinDao(this);
        user = finDao.getUserByID(1);
        theUsername = user.getUsername();thePassword = user.getPassword();
    }

    public void SignUpEvent(View view) {

    }

    public void LogInEvent(View view) {

        //neem de ingevoerde data van de views op, convert ze naar de String data type en sla de waarde op in een variabel
        EditText username= (EditText) findViewById(R.id.username);
        String usernameStr= String.valueOf(username.getText());

        EditText password= (EditText) findViewById(R.id.password);
        String passwordStr= String.valueOf(password.getText());

        //een check op de username en de password
        if (usernameStr.equals(theUsername)&&passwordStr.equals(thePassword)){
            //code die word gecompiled als de password en de username correct zijn

            Intent intent = new Intent(this,StartActivity.class);
            intent.putExtra("username",usernameStr);
            startActivity(intent);

        }else if (usernameStr.equals("")&&passwordStr.equals("")){
            //code die word gecompiled als de velden niet zijn ingevuld

            Toast.makeText(this, "please insert your username and password", Toast.LENGTH_SHORT).show();

        }else{
            //code die word gecompiled als de credentials niet voldoen

            Toast.makeText(this, "invalid credentials", Toast.LENGTH_SHORT).show();
        }


    }

}
