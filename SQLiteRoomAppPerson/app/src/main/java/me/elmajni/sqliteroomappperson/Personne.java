package me.elmajni.sqliteroomappperson;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="PERSONNE")
public class Personne {
    //id column
    @PrimaryKey(autoGenerate = true)
    private int id;

    //name column
    @ColumnInfo(name ="name")
    private String name;

    public Personne() {
    }

    //Generate getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
