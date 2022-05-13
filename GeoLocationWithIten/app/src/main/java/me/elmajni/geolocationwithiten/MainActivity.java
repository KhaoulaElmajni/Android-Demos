package me.elmajni.geolocationwithiten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etSrc, etDst;
    Button tracer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSrc = findViewById(R.id.src);
        etDst = findViewById(R.id.dst);
        tracer = findViewById(R.id.btn_track);

        tracer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String src = etSrc.getText().toString().trim();
                String dst = etDst.getText().toString().trim();

                if (src.equals("") || dst.equals("")){
                    Toast.makeText(MainActivity.this, "veuillez saisir les 2 Localisations", Toast.LENGTH_SHORT).show();
                }else {
                    displayTrajet(src,dst);
                }
            }
        });
    }

    private void displayTrajet(String src, String dst) {
        try{
            Uri uri = Uri.parse("https://maps.google.com.in/maps/dir/"+src+ "/"+dst);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //intent.setData(Uri.parse("geo:47.6788,19.8277"));
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            //when google map is not installed
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}