package com.example.jake.fido.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRetrofit {

    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("usable_type")
    @Expose
    public String usableType;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("address_id")
    @Expose
    public Integer addressId;
    @SerializedName("specialist_id")
    @Expose
    public Integer specialistId;

    /**
     * No args constructor for use in serialization
     *
     */
    public RegisterRetrofit() {
    }

    /**
     *
     * @param email
     * @param name
     * @param gender
     * @param usableType
     * @param specialistId
     * @param password
     * @param addressId
     */
    public RegisterRetrofit(String email, String password, String name, String usableType, String gender, Integer addressId, Integer specialistId) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.usableType = usableType;
        this.gender = gender;
        this.addressId = addressId;
        this.specialistId = specialistId;
    }

    public RegisterRetrofit(String email, String password, String name, String usableType, String gender){
        this.email = email;
        this.password = password;
        this.name = name;
        this.usableType = usableType;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsableType() {
        return usableType;
    }

    public void setUsableType(String usableType) {
        this.usableType = usableType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Integer specialistId) {
        this.specialistId = specialistId;
    }

}