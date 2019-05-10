package com.example.jake.fido.Retrofit.ObjectRetrofit;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doctor  implements Parcelable {
    @SerializedName("likes")
    @Expose
    private Integer likes;

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("doctor_no")
    @Expose
    private String doctorNo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("id_number")
    @Expose
    private String idNumber;
    @SerializedName("id_number_place")
    @Expose
    private String idNumberPlace;
    @SerializedName("id_number_date")
    @Expose
    private String idNumberDate;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address_id")
    @Expose
    private Integer addressId;
    @SerializedName("address_name")
    @Expose
    private String addressName;
    @SerializedName("specialist_id")
    @Expose
    private Integer specialistId;
    @SerializedName("specialist_name")
    @Expose
    private String specialistName;
    @SerializedName("sub_specialist_id")
    @Expose
    private Integer subSpecialistId;
    @SerializedName("sub_specialist_name")
    @Expose
    private String subSpecialistName;
    @SerializedName("hospital_name")
    @Expose
    private String hospitalName;
    @SerializedName("address_details")
    @Expose
    private String addressDetails;
    @SerializedName("longtatude")
    @Expose
    private String longtatude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("employee_id")
    @Expose
    private Integer employeeId;
    @SerializedName("employee_name")
    @Expose
    private String employeeName;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("review")
    @Expose
    private List<Review> review = null;
    @SerializedName("actived")
    @Expose
    private Integer actived;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("experience")
    @Expose
    private String experience;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctorNo() {
        return doctorNo;
    }

    public void setDoctorNo(String doctorNo) {
        this.doctorNo = doctorNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdNumberPlace() {
        return idNumberPlace;
    }

    public void setIdNumberPlace(String idNumberPlace) {
        this.idNumberPlace = idNumberPlace;
    }

    public String getIdNumberDate() {
        return idNumberDate;
    }

    public void setIdNumberDate(String idNumberDate) {
        this.idNumberDate = idNumberDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Integer getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Integer specialistId) {
        this.specialistId = specialistId;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public Integer getSubSpecialistId() {
        return subSpecialistId;
    }

    public void setSubSpecialistId(Integer subSpecialistId) {
        this.subSpecialistId = subSpecialistId;
    }

    public String getSubSpecialistName() {
        return subSpecialistName;
    }

    public void setSubSpecialistName(String subSpecialistName) {
        this.subSpecialistName = subSpecialistName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public String getLongtatude() {
        return longtatude;
    }

    public void setLongtatude(String longtatude) {
        this.longtatude = longtatude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<Review> getReview() {
        return review;
    }
    public void addReview(Review areview){
        review.add(0,areview);
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    public Integer getActived() {
        return actived;
    }

    public void setActived(Integer actived) {
        this.actived = actived;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(specialistName);
        parcel.writeString(description);
        parcel.writeString(addressName);
        parcel.writeString(avatar);
        parcel.writeString(rating);
        parcel.writeString(subSpecialistName);
    }
    public static final Parcelable.Creator<Doctor> CREATOR
            = new Parcelable.Creator<Doctor>() {
        public Doctor createFromParcel(Parcel in) {
            return new Doctor(in);
        }

        @Override
        public Doctor[] newArray(int i) {
            return new Doctor[i];
        }


    };

    private Doctor(Parcel in) {
        name = in.readString();
        specialistName = in.readString();
        description = in.readString();
        addressName = in.readString();
        avatar = in.readString();
        rating = in.readString();
        subSpecialistName = in.readString();
    }
}