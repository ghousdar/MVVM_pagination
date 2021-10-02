package com.example.mvvm.paginationactivity;

public class Speciality {

    String id,name,description,fees,misfees,dutytime,serviceid,image,created;

    int total_pages;


    public Speciality(String id, String name, String description, String fees, String misfees, String dutytime, String serviceid, String image, String created, int total_pages) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fees = fees;
        this.misfees = misfees;
        this.dutytime = dutytime;
        this.serviceid = serviceid;
        this.image = image;
        this.created = created;
        this.total_pages = total_pages;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getMisfees() {
        return misfees;
    }

    public void setMisfees(String misfees) {
        this.misfees = misfees;
    }

    public String getDutytime() {
        return dutytime;
    }

    public void setDutytime(String dutytime) {
        this.dutytime = dutytime;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}

