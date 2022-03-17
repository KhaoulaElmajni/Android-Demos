package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Compte activity onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Compte activity onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Compte activity onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Compte activity onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Compte activity onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Compte activity onDestroy", Toast.LENGTH_SHORT).show();
    }
}