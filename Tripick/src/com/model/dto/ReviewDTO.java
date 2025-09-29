package com.model.dto;

public class ReviewDTO {
    private String reviewNo;
    private String userNo;
    private String travelNo;
    private String reviewTitle;
    private String content;
    private String rate;
    private String createdAt;
    private String updatedAt;
    private String travelTitle;
    private String userNickName;

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "reviewNo='" + reviewNo + '\'' +
                ", userNo='" + userNo + '\'' +
                ", travelNo='" + travelNo + '\'' +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", content='" + content + '\'' +
                ", rate='" + rate + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", travelTitle='" + travelTitle + '\'' +
                ", userNickName='" + userNickName + '\'' +
                '}';
    }

    public String getTravelTitle() {
        return travelTitle;
    }

    public void setTravelTitle(String travelTitle) {
        this.travelTitle = travelTitle;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(String reviewNo) {
        this.reviewNo = reviewNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getTravelNo() {
        return travelNo;
    }

    public void setTravelNo(String travelNo) {
        this.travelNo = travelNo;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
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
