package com.example.mvvm.retrofitclass;

public class Bankpojo {


    String id,name,code,image;

    public Bankpojo(String id, String name, String code, String image) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
