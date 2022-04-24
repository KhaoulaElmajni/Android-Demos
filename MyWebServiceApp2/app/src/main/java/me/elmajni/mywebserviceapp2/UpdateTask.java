package me.elmajni.mywebserviceapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText name,details;
    Button update;
    String[] statusArray = { "WAITING","DONE","PENDING" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        name = findViewById(R.id.taskupname);
        details = findViewById(R.id.taskupdetails);
        update = findViewById(R.id.btnUpdate);

        @SuppressLint("WrongViewCast") Spinner spinner = findViewById(R.id.taskstatus);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter ad= new ArrayAdapter(this, android.R.layout.simple_spinner_item, statusArray);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spinner.setAdapter(ad);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),statusArray[i],Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}