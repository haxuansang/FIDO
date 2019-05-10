package com.example.jake.fido.Retrofit.ObjectRetrofit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("code")
    @Expose
    private Object code;
    @SerializedName("id")
    @Expose
    private Integer id;

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}