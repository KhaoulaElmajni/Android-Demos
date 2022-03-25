package me.elmajni.contactapp;

public class ItemModel {
    private Long id;
    private String name;
    private String email;
    private String job;
    private String phone;

    public ItemModel() {
    }
    public ItemModel(Long id, String name, String email, String job, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.job = job;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
