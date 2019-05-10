package com.example.jake.fido.Retrofit.ReviewObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewUp {

    @SerializedName("star")
    @Expose
    private String star;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("patient_id")
    @Expose
    private Integer patientId;

    /**
     * No args constructor for use in serialization
     *
     */
    public ReviewUp() {
    }

    /**
     *
     * @param patientId
     * @param star
     * @param review
     */
    public ReviewUp(String star, String review, Integer patientId) {
        super();
        this.star = star;
        this.review = review;
        this.patientId = patientId;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

}