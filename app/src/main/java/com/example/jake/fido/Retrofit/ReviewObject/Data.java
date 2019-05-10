package com.example.jake.fido.Retrofit.ReviewObject;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("star")
    @Expose
    private String star;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("doctor_id")
    @Expose
    private Integer doctorId;
    @SerializedName("doctor_name")
    @Expose
    private String doctorName;
    @SerializedName("patient_id")
    @Expose
    private Integer patientId;
    @SerializedName("patient_name")
    @Expose
    private String patientName;
    @SerializedName("patient_avatar")
    @Expose
    private String patientAvatar;
    @SerializedName("like")
    @Expose
    private Integer like;
    @SerializedName("report")
    @Expose
    private Integer report;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public Data() {
    }

    /**
     *
     * @param updatedAt
     * @param id
     * @param patientAvatar
     * @param patientId
     * @param patientName
     * @param doctorId
     * @param createdAt
     * @param report
     * @param star
     * @param like
     * @param doctorName
     * @param review
     */
    public Data(Integer id, String star, String review, Integer doctorId, String doctorName, Integer patientId, String patientName, String patientAvatar, Integer like, Integer report, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.star = star;
        this.review = review;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientAvatar = patientAvatar;
        this.like = like;
        this.report = report;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAvatar() {
        return patientAvatar;
    }

    public void setPatientAvatar(String patientAvatar) {
        this.patientAvatar = patientAvatar;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getReport() {
        return report;
    }

    public void setReport(Integer report) {
        this.report = report;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}