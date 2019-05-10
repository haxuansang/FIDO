package com.example.jake.fido.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPassword {

    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResetPassword() {
    }

    /**
     *
     * @param message
     * @param statusCode
     */
    public ResetPassword(Integer statusCode, String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}