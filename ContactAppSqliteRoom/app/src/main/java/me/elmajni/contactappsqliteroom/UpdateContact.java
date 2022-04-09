package me.elmajni.contactappsqliteroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

public class UpdateContact extends AppCompatActivity {
    private AppDatabase db;
    private FloatingActionButton fab;
    private EditText name,job,email,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_view);
        fab = findViewById(R.id.fab);
        name = findViewById(R.id.name);
        job = findViewById(R.id.job);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateContact.this,MainActivity.class);
                startActivity(intent);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Gson gson = new Gson();
        Contact contact = gson.fromJson(getIntent().getStringExtra("contactJson"), Contact.class);
        name.setText(contact.getName());
        phone.setText(contact.getPhone());
        job.setText(contact.getJob());
        email.setText(contact.getEmail());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}