package com.controller;

import com.model.dto.ReviewDTO;
import com.service.ReviewService;

import javax.swing.text.View;
import java.util.ArrayList;

public class ReviewController {

    private ReviewService reviewService = new ReviewService();

    public void searchFromLoc(int travelNo) {
        View view = new View();
        ArrayList<ReviewDTO> dtoList = reviewService.searchFromLoc(travelNo);
        if (!dtoList.isEmpty()) {
            view.displayReviewList(dtoList);
        } else {
            view.displayNoData();
        }
    }

    public void searchFromWriter(String nickName) {
        View view = new View();

        ArrayList<ReviewDTO> dtoList = reviewService.searchFromWriter(nickName);
        if (!dtoList.isEmpty()) {
            view.displayReviewList(dtoList);
        } else {
            view.displayNoData();
        }
    }

    public void readDetailReview(int reviewNo) {

        ReviewDTO reviewDTO = reviewService.readDetailReview(reviewNo);
        if (reviewDTO != null) {
            view.displayReivew(reviewDTO);
        } else {
            view.displayNoData();
        }
    }

    public void insertReview(ReviewDTO reviewDTO) {
        int rs = reviewService.insertReview(reviewDTO);
        if (rs > 0) {
            new View().displaySuccess("리뷰 생성 성공");
        } else {
            new View().displayError("리뷰 생성 실패");
        }
    }

    public void modifyReview(ReviewDTO reviewDTO) {
        int rs = reviewService.modifyReview(reviewDTO);
        if (rs > 0) {
            new View().displaySuccess("리뷰 수정 성공");
        } else {
            new View().displaySuccess("리뷰 수정 실패");
        }
    }

    public void deleteReview(int reviewNo) {
        int rs = reviewService.deleteReview(reviewNo);
        if (rs > 0) {
            new View().displaySuccess("리뷰 삭제 성공");
        } else {
            new View().displayError("리뷰 삭제 실패");
        }
    }
}
