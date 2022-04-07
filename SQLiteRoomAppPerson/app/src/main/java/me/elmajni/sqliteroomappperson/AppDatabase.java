package me.elmajni.sqliteroomappperson;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Add database entities
@Database(entities={Personne.class},version=1,exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    // create database instance
    private static AppDatabase database;

    // Define database name
    private static String DATABASE_NAME="PersonneDB";

    public synchronized static AppDatabase getInstance(Context context)
    {
        // check condition
        if(database==null)
        {
            // when database is null
            // Initialize database
            database= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        // Return database
        return database;
    }

    //Create DAO
    public abstract DAO mainDao();
}
