package com.example.jake.fido.Retrofit;


import com.example.jake.fido.Retrofit.ObjectRetrofit.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRetrofit {

    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("token_type")
    @Expose
    public  String tokenType;
    @SerializedName("expires_in")
    @Expose
    public  Integer expiresIn;
    @SerializedName("data")
    @Expose
    public  Data data;
    @SerializedName("status_code")
    @Expose
    public  Integer statusCode;
    @SerializedName("usable_type")
    @Expose
    public  String usableType;
    @SerializedName("usable_id")
    @Expose
    public  Integer usableId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }



    public String getUsableType() {
        return usableType;
    }

    public void setUsableType(String usableType) {
        this.usableType = usableType;
    }

    public Integer getUsableId() {
        return usableId;
    }

    public void setUsableId(Integer usableId) {
        this.usableId = usableId;
    }

}