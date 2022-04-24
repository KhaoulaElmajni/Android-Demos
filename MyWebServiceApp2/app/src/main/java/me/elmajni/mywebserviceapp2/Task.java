package me.elmajni.mywebserviceapp2;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String name;
    private String status;
    private String details;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Task() {
    }

    public Task(String name) {
        this.name = name;
    }

    public Task( String name,String status,String details) {
        this.name = name;
        this.status = status;
        this.details = details;
    }

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
