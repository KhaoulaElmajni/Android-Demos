package me.elmajni.sqliteroomappperson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

// Initialize variable

    EditText editText;
    Button btAdd, btReset;
    RecyclerView recyclerView;

    List<Personne> dataList=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    AppDatabase database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable

        editText=findViewById(R.id.name);
        btAdd=findViewById(R.id.btnAdd);
        btReset=findViewById(R.id.btnRst);
        recyclerView=findViewById(R.id.recyclerView);

        // initialize database
        database=AppDatabase.getInstance(this);

        // store database value in data list

        dataList=database.mainDao().getAll();

        //Initialize linear layout manager
        linearLayoutManager=new LinearLayoutManager(this);

        // Set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);

        // Initialize adapter
        adapter=new MainAdapter(MainActivity.this,dataList);

        // set adapter

        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get string from edit text
                String sText=editText.getText().toString().trim();

                // check condition
                if(!sText.equals(""))
                {
                    // when text is not empty
                    // initialize main data

                    Personne data=new Personne();

                    //Set text on main data
                    data.setName(sText);

                    //Insert text in database
                    database.mainDao().insert(data);

                    //Clear edit text
                    editText.setText("");

                    //Notify when data is inserted
                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();

                }
            }
        });


        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete
                database.mainDao().reset(dataList);

                //Notify when data is inserted
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });

    }

}
