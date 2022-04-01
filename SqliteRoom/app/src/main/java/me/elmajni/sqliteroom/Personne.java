package me.elmajni.sqliteroom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class Personne {
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name ="name")
    private String name;
}
