package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        if (usernameInput.getText().equals(null) && pwdInput.getText().equals(null)){
            Toast.makeText(this, "Attention! les shamps doivent etre rempli!!!", Toast.LENGTH_SHORT).show();
        }else {
            Intent activite2 = new Intent(this,Compte.class);
            Bundle container = new Bundle();
            container.putString("username",usernameInput.getText().toString());
            container.putString("password",pwdInput.getText().toString());
            activite2.putExtras(container);
            startActivity(activite2);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Login activity onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Login activity onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Login activity onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Login activity onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Login activity onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Login activity onDestroy", Toast.LENGTH_SHORT).show();
    }
}