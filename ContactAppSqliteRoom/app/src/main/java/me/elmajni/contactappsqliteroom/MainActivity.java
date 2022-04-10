package me.elmajni.contactappsqliteroom;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Contact> contacts =new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    AppDatabase db;
    MainAdapter adapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddContact.class);
                startActivity(intent);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView=findViewById(R.id.recyclerView);

        //initialize database
        db =AppDatabase.getInstance(this);
        //get data in personnes list
        contacts = db.contactDao().getAll();

        //initialize & set linear layout manager in recycleview
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        /*Contact contact=new Contact();
        contact.setName("khaoula elmajni");
        contact.setJob("Software Engineer");
        contact.setPhone("0639127097");
        contact.setEmail("elmajnikhaoula@gmail.Com");
        //insert personne in db
        db.contactDao().insert(contact);
        Contact contact1=new Contact();
        contact1.setName("khaoula elmajni");
        contact1.setJob("Software Engineer");
        contact1.setPhone("0639127097");
        contact1.setEmail("elmajnikhaoula@gmail.Com");
        //insert personne in db
        db.contactDao().insert(contact);*/
        //initialize & set adapter
        adapter=new MainAdapter(MainActivity.this, contacts, new MainAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Contact contact) {
                Intent intent = new Intent(MainActivity.this,UpdateContact.class);
                Gson gson = new Gson();
                String contactJson = gson.toJson(contact);
                intent.putExtra("contactJson", contactJson);
                startActivity(intent);
            }
            @Override
            public void onItemLongClick(int position) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
//set icon
                        .setIcon(R.drawable.ic_warn)
//set title
                        .setTitle("Are you sure to delete")
//set message
                        .setMessage("delete this contact")
//set positive button
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what would happen when positive button is clicked
                                //contacts.remove(position);
                                db.contactDao().delete(contacts.get(position));
                                contacts = db.contactDao().getAll();
                                adapter.notifyItemRemoved(position);
                                finish();
                            }
                        })
//set negative button
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what should happen when negative button is clicked

                                Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });
        recyclerView.setAdapter(adapter);
        //recyclerView.scrollToPosition(contacts.size()-1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        //la recherche
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override //every change of carac
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
                case R.id.search:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}