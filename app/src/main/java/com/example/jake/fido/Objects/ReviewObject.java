package com.example.jake.fido.Objects;

public class ReviewObject {
    String contentReview;
    Float numRating;

    public ReviewObject(String contentReview, Float numRating) {
        this.contentReview = contentReview;
        this.numRating = numRating;
    }

    public String getContentReview() {
        return contentReview;
    }

    public void setContentReview(String contentReview) {
        this.contentReview = contentReview;
    }

    public Float getNumRating() {
        return numRating;
    }

    public void setNumRating(Float numRating) {
        this.numRating = numRating;
    }

    public ReviewObject(){

    }
}
