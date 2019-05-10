package com.example.jake.fido.Retrofit.ReviewObject;
import com.example.jake.fido.Retrofit.ObjectRetrofit.Review;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewResponse {

    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("data")
    @Expose
    private Review data;


    public ReviewResponse() {
    }


    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Review getData() {
        return data;
    }

    public void setData(Review data) {
        this.data = data;
    }
}