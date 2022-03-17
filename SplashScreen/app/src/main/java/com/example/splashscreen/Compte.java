package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Compte extends AppCompatActivity {
    private TextView username, password;
    Button retour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);
        setAllViews();
        Bundle b = getIntent().getExtras();
        String name = b.getString("username");
        String pwd = b.getString("password");

        username.setText(username.getText()+name);
        password.setText(password.getText()+pwd);
    }

    void setAllViews(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        retour = findViewById(R.id.retour);
    }

    void retour(View view){
        Intent myIntent = new Intent(this,MainActivity.class);
    }
}