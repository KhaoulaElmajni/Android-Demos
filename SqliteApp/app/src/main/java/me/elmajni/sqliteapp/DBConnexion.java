package me.elmajni.sqliteapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBConnexion extends SQLiteOpenHelper {

    public DBConnexion(Context context) {
        super(context, "Personnes.db", null, 1);
    }
    public void insertNewPersonne(String name){
        //SQLiteDatabase wDB = this.getWritableDatabase();
       // wDB.execSQL("INSERT INTO Personnes(Name) VALUES ("+name+")");
        SQLiteDatabase wDB = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("Name", name);
        wDB.insert("Personnes",null,contentValue);
    }

    public void deleteRow(Integer id){
        SQLiteDatabase wDB = this.getWritableDatabase();
        wDB.execSQL("delete from Personnes where id="+String.valueOf(id));
    }

    public void updateRow(String name, Integer id){
        SQLiteDatabase wDB = this.getWritableDatabase();
        wDB.execSQL("update Personnes set Name='"+name+"' where ID="+String.valueOf(id));
    }

    @SuppressLint("Range")
    public ArrayList getAllRecord(){
        ArrayList maliste = new ArrayList();
        SQLiteDatabase rDB = this.getReadableDatabase();
        Cursor res = rDB.rawQuery("Select * FROM Personnes", null);
        res.moveToFirst();
        while(res.isAfterLast()== false){
            maliste.add(res.getString(res.getColumnIndex("ID"))+ ": " + res.getString(res.getColumnIndex("Name")));
                    res.moveToNext();
        }
        return maliste;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Personnes(ID INTEGER PRIMARY KEY, Name TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Personnes");
        onCreate(db);
    }
}

