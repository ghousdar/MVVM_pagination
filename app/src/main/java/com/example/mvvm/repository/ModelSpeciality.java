package com.example.mvvm.repository;

import com.example.mvvm.paginationactivity.Speciality;

public class ModelSpeciality {
    Speciality[] specialities;
    int total_pages;

    public ModelSpeciality(int total_pages) {
        this.total_pages = total_pages;
    }

    public Speciality[] getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Speciality[] specialities) {
        this.specialities = specialities;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}
