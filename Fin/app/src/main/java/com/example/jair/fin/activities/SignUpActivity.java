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

import static com.example.jair.fin.schema.Schema.SchemaUser.EMAIL;
import static com.example.jair.fin.schema.Schema.SchemaUser.USER_NAME;
import static com.example.jair.fin.schema.Schema.SchemaUser.USER_PASSWORD;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_act);
    }

    public void SignUpEvent(View view) {
        //get data from xml
        EditText usernameView = (EditText) findViewById(R.id.signup_username);
        EditText passwordView = (EditText) findViewById(R.id.signup_password);
        EditText emailView = (EditText) findViewById(R.id.signup_email);
        EditText passwordView2 = (EditText) findViewById(R.id.signup_password_confirm);

        String username = String.valueOf(usernameView.getText());
        String email = String.valueOf(emailView.getText());
        String password = String.valueOf(passwordView.getText());
        String password2 = String.valueOf(passwordView2.getText());

        if (password.equals(password2)){

            ContentValues contentValues=new ContentValues();
            contentValues.put(USER_NAME,username);
            contentValues.put(EMAIL,email);
            contentValues.put(USER_PASSWORD,password);

            FinDao finDao = new FinDao(this);
            if (finDao.insertUser(contentValues)) {


                Toast.makeText(this, "sign up successful", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this, StartActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }else{
                Toast.makeText(this, "signup unsuccesful", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "passwords do not match", Toast.LENGTH_SHORT).show();
        }


    }
}
