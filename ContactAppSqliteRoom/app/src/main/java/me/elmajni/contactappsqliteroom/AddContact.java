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

public class AddContact extends AppCompatActivity {
    private FloatingActionButton fab;
    private AppDatabase db;
    private EditText name,job,phone,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        fab = findViewById(R.id.fab);
        setAllViews();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddContact.this,MainActivity.class);
                startActivity(intent);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        //initialize database
        db =AppDatabase.getInstance(this);

    }

    private void setAllViews() {
        name = findViewById(R.id.name);
        job = findViewById(R.id.job);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_contact_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save:
                Contact contact=new Contact();
                contact.setName(name.getText().toString());
                contact.setJob(job.getText().toString());
                contact.setPhone(phone.getText().toString());
                contact.setEmail(email.getText().toString());
                //insert personne in db
                db.contactDao().insert(contact);
                refreshViews();
                break;
            case R.id.refresh:
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