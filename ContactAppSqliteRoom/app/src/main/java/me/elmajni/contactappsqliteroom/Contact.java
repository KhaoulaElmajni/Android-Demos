package me.elmajni.contactappsqliteroom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName ="CONTACT")
public class Contact implements Serializable {
    //id column
    @PrimaryKey(autoGenerate = true)
    private int id;

    //name column
    @ColumnInfo(name ="name")
    private String name;
    private String email;
    private String job;
    private String phone;

    public Contact() {
    }
    public Contact(String name, String email, String job, String phone) {
        this.name = name;
        this.email = email;
        this.job = job;
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", job='" + job + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
