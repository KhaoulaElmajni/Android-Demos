package me.elmajni.contactappsqliteroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
                Snackbar.make(view, "Liste des contacts", Snackbar.LENGTH_LONG)
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
                if (name.getText().toString().equals("") || job.getText().toString().equals("") ||
                        phone.getText().toString().equals("") || email.getText().toString().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    //Setting message manually and performing action on button click
                    builder.setMessage("Un ou plusieurs de vos shmaps est vide!!!\nVeuillez remplir tous les shamps.")
                            .setCancelable(false)
                            .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                    Toast.makeText(AddContact.this, "Vous avez quitter l'p??ration d'ajout", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(),"Continuer le remplissage des shamps",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Attention!");
                    alert.setIcon(R.drawable.ic_warn);
                    alert.show();
                }else {
                    Contact contact=new Contact();
                    contact.setName(name.getText().toString());
                    contact.setJob(job.getText().toString());
                    contact.setPhone(phone.getText().toString());
                    contact.setEmail(email.getText().toString());
                    int nbr = getRandomNumber(1,12);
                    contact.setPhoto("photo"+nbr);
                    //insert personne in db
                    db.contactDao().insert(contact);
                    Intent intent =new Intent(AddContact.this,MainActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.refresh:
                refreshViews();
                break;
            case R.id.settings:
                startActivity(new Intent(this, GeneralSettings.class));
                break;

            case R.id.theme:
            startActivity(new Intent(this, GeneralSettings.class));
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
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}