package com.example.retrofix2.volley;

import java.io.Serializable;

public class User implements
        Serializable {
    public User( int id, String gender, String email, String name,String images) {
        this.id = id;
        this.gender = gender;
        this.email = email;
        this.name = name;
        this.images = images;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
    private int id;
    private String name, email, gender, images;
}