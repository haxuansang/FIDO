package com.example.jake.fido.Objects;

public class DoctorObjects {
    public String name,major,description,address,imageLink;
    public double evaluation;

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public DoctorObjects(String name, String major, String description, String address, String imageLink, int evaluation) {

        this.name = name;
        this.major = major;
        this.description = description;
        this.address = address;
        this.imageLink = imageLink;
        this.evaluation = evaluation;
    }

    public DoctorObjects(String name, String major, String description, String address, double evaluation) {
        this.name = name;

        this.major = major;
        this.description = description;
        this.address = address;
        this.evaluation = evaluation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }
}
