package me.elmajni.myweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout home;
    private ProgressBar loading;
    private TextView cityNameTV, temperatureTV,condition;
    private RecyclerView weatherRV;
    private TextInputEditText cityEDT;
    private ImageView backIV, iconIV,searchIV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = findViewById(R.id.home);
        loading = findViewById(R.id.pbLoading);
        cityNameTV = findViewById(R.id.cityname);
        temperatureTV =findViewById(R.id.idTVtemperature);
        condition =findViewById(R.id.idTVCondition);
        weatherRV =findViewById(R.id.idRvWeather);
        cityEDT =findViewById(R.id.idEtCity);
        backIV =findViewById(R.id.background);
        iconIV =findViewById(R.id.idTVIcon);
        searchIV =findViewById(R.id.idTVsearch);



    }
}