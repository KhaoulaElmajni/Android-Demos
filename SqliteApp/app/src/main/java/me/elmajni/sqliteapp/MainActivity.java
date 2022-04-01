package me.elmajni.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button save,update,delete;
    private DBConnexion dbConnexion;
    private TextView id, name;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAllViews();
        dbConnexion = new DBConnexion(this);
        lv = findViewById(R.id.liste);
        ArrayAdapter myAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        lv.setAdapter(myAdapter);
    }

    public void update(View view) {
        dbConnexion.updateRow(name.getText().toString(),Integer.parseInt(id.getText().toString()));
        ArrayList<String> arrayListe = dbConnexion.getAllRecord();
        ArrayAdapter myAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayListe);
        lv.setAdapter(myAdapter);
        name.setText("");
        id.setText("");
    }

    public void delete(View view) {
        dbConnexion.deleteRow(Integer.parseInt(id.getText().toString()));
        ArrayList<String> arrayL = dbConnexion.getAllRecord();
        ArrayAdapter myAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayL);
        lv.setAdapter(myAdapter);
        id.setText("");
    }

    public void save(View view) {
        dbConnexion.insertNewPersonne(name.getText().toString());
        ArrayList<String> arrayListe = dbConnexion.getAllRecord();
        ArrayAdapter myAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayListe);
        lv.setAdapter(myAdapter);
        name.setText("");
    }

    public void setAllViews(){
        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
    }
}