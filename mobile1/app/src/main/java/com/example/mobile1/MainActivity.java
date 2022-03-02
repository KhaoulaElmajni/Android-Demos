package com.example.mobile1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.const2nd);

        final EditText nbr1;
        final EditText nbr2;
        Button som ;
        Button reset;
        TextView result;
        nbr1 = (EditText) findViewById(R.id.nbr1);
        nbr2 = (EditText) findViewById(R.id.editTextNumber2);
        som = (Button) findViewById(R.id.somme);
        reset = (Button) findViewById(R.id.rst);
        result = (TextView) findViewById(R.id.result);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nbr1.setText("");
                nbr2.setText("");
                result.setText("");
            }
        });
    }

    public void somme(View view){
        EditText nbr1;
        EditText nbr2;
        Button somm;
        int s;
        TextView result;
        nbr1 = (EditText) findViewById(R.id.nbr1);
        nbr2 = (EditText) findViewById(R.id.editTextNumber2);
        somm = (Button) findViewById(R.id.somme);
        result = (TextView) findViewById(R.id.result);
        s = Integer.parseInt(nbr1.getText().toString())+Integer.parseInt(nbr2.getText().toString());
        result.setText(String.valueOf(s));
    }
}