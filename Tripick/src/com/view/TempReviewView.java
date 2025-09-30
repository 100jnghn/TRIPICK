package com.view;

import com.controller.ReviewController;
import com.model.dto.ReviewDTO;

import java.util.ArrayList;

public class TempReviewView {

    public void test() {
        ReviewController con = new ReviewController();
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewNo(902);
        reviewDTO.setReviewTitle("리뷰 수정 테스트");
        reviewDTO.setContent("리뷰 수정 테스트, 내용");
        reviewDTO.setRate(3);

        con.deleteReview(902);
    }

    public void displayReviewList(ArrayList<ReviewDTO> dtoList) {
        for (ReviewDTO reviewDTO : dtoList) {
            displayReview(reviewDTO);
        }
    }

    public void displayReview(ReviewDTO reviewDTO) {
        System.out.println(reviewDTO);
    }

    //여기부터 공통

    public void displayNoData() {
        System.out.println("No data");
    }

    public void displaySuccess(String message) {
        System.out.println(message);
    }

    public void displayError(String message) {
        System.out.println(message);
    }
}
