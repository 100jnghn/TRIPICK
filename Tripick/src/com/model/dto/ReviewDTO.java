package com.model.dto;

import java.util.StringJoiner;

public class ReviewDTO {
    private Integer reviewNo;
    private Integer userNo;
    private Integer travelNo;
    private String reviewTitle;
    private String content;
    private Integer rate;
    private String createdAt;
    private String updatedAt;
    private String travelTitle;
    private String userNickName;


    @Override
    public String toString() {
        StringJoiner j = new StringJoiner(", ", "{ ", " }");

        if (reviewNo != null) j.add("reviewNo='" + reviewNo + "'");
        if (userNo != null) j.add("userNo='" + userNo + "'");
        if (travelNo != null) j.add("travelNo='" + travelNo + "'");
        if (userNickName != null) j.add("작성자='" + userNickName + "'");
        if (reviewTitle != null) j.add("Review 제목='" + reviewTitle + "'");
        if (content != null) j.add("내용='" + content + "'");
        if (rate != null) j.add("평점='" + rate + "'");
        if (updatedAt != null) {
            j.add("수정일='" + updatedAt + "'");
        } else {
            j.add("작성일='" + createdAt + "'");
        }
        if (travelTitle != null) j.add("여행지='" + travelTitle + "'");

        return j.toString();
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

    public int getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(int reviewNo) {
        this.reviewNo = reviewNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getTravelNo() {
        return travelNo;
    }

    public void setTravelNo(int travelNo) {
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
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
