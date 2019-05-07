package com.example.jake.fido.Retrofit.ObjectRetrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("status")
    @Expose
    private Object status;
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
    @SerializedName("passport_no")
    @Expose
    private Object passportNo;
    @SerializedName("passport_place")
    @Expose
    private Object passportPlace;
    @SerializedName("passport_date")
    @Expose
    private Object passportDate;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address_id")
    @Expose
    private Integer addressId;
    @SerializedName("employee_id")
    @Expose
    private Integer employeeId;
    @SerializedName("specialist_id")
    @Expose
    private String specialistId;
    @SerializedName("hospital_name")
    @Expose
    private String hospitalName;
    @SerializedName("address_details")
    @Expose
    private String addressDetails;
    @SerializedName("sub_specialist_id")
    @Expose
    private String subSpecialistId;
    @SerializedName("longtatude")
    @Expose
    private Object longtatude;
    @SerializedName("latitude")
    @Expose
    private Object latitude;
    @SerializedName("certificates")
    @Expose
    private Object certificates;
    @SerializedName("actived")
    @Expose
    private Integer actived;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("office")
    @Expose
    private Object office;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
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

    public Object getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(Object passportNo) {
        this.passportNo = passportNo;
    }

    public Object getPassportPlace() {
        return passportPlace;
    }

    public void setPassportPlace(Object passportPlace) {
        this.passportPlace = passportPlace;
    }

    public Object getPassportDate() {
        return passportDate;
    }

    public void setPassportDate(Object passportDate) {
        this.passportDate = passportDate;
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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(String specialistId) {
        this.specialistId = specialistId;
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

    public String getSubSpecialistId() {
        return subSpecialistId;
    }

    public void setSubSpecialistId(String subSpecialistId) {
        this.subSpecialistId = subSpecialistId;
    }

    public Object getLongtatude() {
        return longtatude;
    }

    public void setLongtatude(Object longtatude) {
        this.longtatude = longtatude;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getCertificates() {
        return certificates;
    }

    public void setCertificates(Object certificates) {
        this.certificates = certificates;
    }

    public Integer getActived() {
        return actived;
    }

    public void setActived(Integer actived) {
        this.actived = actived;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Object getOffice() {
        return office;
    }

    public void setOffice(Object office) {
        this.office = office;
    }

}