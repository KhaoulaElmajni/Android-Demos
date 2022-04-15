package me.elmajni.contactappsqliteroom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities={Contact.class},version=2,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    // create database instance
    private static AppDatabase appDatabase;
    public static AppDatabase getInstance(Context context) {
        // check condition
        if(appDatabase==null)
        {
            //initialize database
            appDatabase= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"ContactDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        /*
        * appDatabase = Room.databaseBuilder(getApplicationContext(),
         AppDatabase.class, "PersonneDB").build();
        */
        }
        //return db
        return appDatabase;
    }
    //create DAO method that returns DAO obj
    public abstract DAO contactDao();
}