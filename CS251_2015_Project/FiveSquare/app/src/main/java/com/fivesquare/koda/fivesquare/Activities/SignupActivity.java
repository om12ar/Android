package com.fivesquare.koda.fivesquare.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fivesquare.koda.fivesquare.Controllers.DBController;
import com.fivesquare.koda.fivesquare.R;

public class SignupActivity extends AppCompatActivity {

    DBController db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            db = new DBController(this);
        }
        catch (Exception e){
            Toast.makeText(SignupActivity.this, "ERROR1 " + e.toString(), Toast.LENGTH_LONG).show();
        }
        setContentView(R.layout.activity_signup);
        createUser();

    }
    public void createUser(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText nameET = (EditText) findViewById(R.id.nameET);
                final EditText passET = (EditText) findViewById(R.id.passET);
                final EditText emailET = (EditText) findViewById(R.id.emailET);

                boolean isInserted  = db.addUser(nameET.getText().toString() , emailET.getText().toString(),passET.getText().toString());
                Log.i("SIGNUP",""+isInserted);
                if (isInserted){
                    Toast.makeText(SignupActivity.this,"Account created successfully" ,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(SignupActivity.this,"This email is already registered" ,Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
