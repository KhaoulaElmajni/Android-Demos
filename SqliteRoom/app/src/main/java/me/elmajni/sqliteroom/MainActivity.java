package me.elmajni.sqliteroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
        AppDatabase.class, "Personnes").build();
        PersonneDAO personneDao = db.personneDao();
        List<Personne> personnes = personneDao.getAll();
    }
}