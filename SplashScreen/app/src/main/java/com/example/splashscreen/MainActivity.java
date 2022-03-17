package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView username, password;
    private EditText usernameInput,pwdInput;
    private Button authenticate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAllViews();
    }
    void setAllViews(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        usernameInput = findViewById(R.id.usernameInput);
        pwdInput = findViewById(R.id.passwordInput);
    }

    public void envoyer(View view) {
        Intent activite2 = new Intent(this,Compte.class);
        Bundle container = new Bundle();
        container.putString("username",usernameInput.getText().toString());
        container.putString("password",pwdInput.getText().toString());
        activite2.putExtras(container);
        startActivity(activite2);
    }
}