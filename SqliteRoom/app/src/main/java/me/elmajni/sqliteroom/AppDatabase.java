package me.elmajni.sqliteroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Personne.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonneDAO personneDao();
}

