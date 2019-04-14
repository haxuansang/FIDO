package com.example.jake.fido.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class DoctorObjects implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(major);
        parcel.writeString(description);
        parcel.writeString(address);
        parcel.writeString(imageLink);
        parcel.writeDouble(evaluation);
    }
    public static final Parcelable.Creator<DoctorObjects> CREATOR
            = new Parcelable.Creator<DoctorObjects>() {
        public DoctorObjects createFromParcel(Parcel in) {
            return new DoctorObjects(in);
        }

        @Override
        public DoctorObjects[] newArray(int i) {
            return new DoctorObjects[i];
        }


    };

    private DoctorObjects(Parcel in) {
        name = in.readString();
        major = in.readString();
        description = in.readString();
        address = in.readString();
        imageLink = in.readString();
        evaluation = in.readDouble();
    }
}
