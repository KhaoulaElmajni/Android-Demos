package me.elmajni.contactappsqliteroom;

import androidx.annotation.NonNull;
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
        setAllViews();
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

    private void setAllViews() {
        name = findViewById(R.id.name);
        job = findViewById(R.id.job);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.check:
                //get Updated person from edit text
                String nameTxt=name.getText().toString().trim();
                String jobTxt=job.getText().toString().trim();
                String phoneTxt=phone.getText().toString().trim();
                String emailTxt=email.getText().toString().trim();
                //update person in DB
                //db.contactDao().update(ID, nameTxt,jobTxt,emailTxt,phoneTxt);
                refreshViews();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshViews() {
        name.setText("");
        job.setText("");
        email.setText("");
        phone.setText("");
    }
}