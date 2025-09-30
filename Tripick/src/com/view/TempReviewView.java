package com.view;

import com.controller.ReviewController;
import com.model.dto.ReviewDTO;

import java.util.ArrayList;
import java.util.Scanner;


public class TempReviewView {
    public Scanner sc = new Scanner(System.in);

    public void test() {
        ReviewController con = new ReviewController();
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewNo(891);
        reviewDTO.setReviewTitle("리뷰 수정 테스트");
        reviewDTO.setContent("리뷰 수정 테스트, 내용");
        reviewDTO.setRate(3);

        con.modifyReview(reviewDTO);
    }


    public void middleMenu() {
        displayMiddleMenu();

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                //관관지 메뉴 메소드 호출
                break;
            case 2://리뷰 메뉴로 이동
                reviewMenu();
                break;
            case -1://종료
                return;
        }

    }

    private void reviewMenu() {
        displayReviewMenu();
        ReviewController con = new ReviewController();

        int choice = sc.nextInt();
        switch (choice) {
            case 1://검색

                break;
            case 2://등록
                ReviewDTO insertDTO = insertMenu();
                con.insertReview(insertDTO);
                break;

            case 3://수정
                ReviewDTO modifyDTO = null;
                break;
            case 4://삭제
                break;
        }
    }

    private ReviewDTO insertMenu() {
        ReviewDTO dto = new ReviewDTO();
        System.out.println("=================================");
        System.out.println("===========리뷰 등록 화면===========");
        System.out.print("관광지 번호 입력 >> ");
        dto.setTravelNo(sc.nextInt());
        sc.nextLine();
        System.out.print("리뷰 제목 입력 >> ");
        dto.setReviewTitle(sc.nextLine());
        System.out.println("리뷰 내용 입력 >> ");
        dto.setContent(sc.nextLine());

        return dto;
    }

    private void displayReviewMenu() {
        System.out.println("=================================");
        System.out.println("=============메뉴 선택=============");
        System.out.println("-1. 종료");
        System.out.println("1. 리뷰 검색");
        System.out.println("2. 리뷰 등록");
        System.out.println("3. 리뷰 수정");
        System.out.println("4. 리뷰 삭제");
        System.out.print("입력 >> ");
    }

    private void displayMiddleMenu() {
        System.out.println("=================================");
        System.out.println("=============메뉴 선택=============");
        System.out.println("-1. 종료");
        System.out.println("1. 관광지 메뉴로 이동");
        System.out.println("2. 리뷰 메뉴로 이동");
        System.out.print("입력 >> ");
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
