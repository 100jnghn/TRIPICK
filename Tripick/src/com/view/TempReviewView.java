package com.view;

import com.controller.ReviewController;
import com.controller.UserController;
import com.model.dto.ReviewDTO;

import java.util.ArrayList;
import java.util.Scanner;


public class TempReviewView {
    public Scanner sc = new Scanner(System.in);

    public void middleMenu() {
        while (true) {
            displayMiddleMenu();

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    //관관지 메뉴 메소드 호출
                    TempTravelViewJW tempTravelView = new TempTravelViewJW();
                    tempTravelView.inputTravelMenu();
                    break;
                case 2://리뷰 메뉴로 이동
                    reviewMenu();
                    break;
                case -1://종료
                    return;
            }
        }

    }

    private void reviewMenu() {
        while (true) {
            displayReviewMenu();
            ReviewController con = new ReviewController();

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1://검색
                    searchReview();
                    break;
                case 2://등록
                    ReviewDTO insertDTO = insertMenu();
                    con.insertReview(insertDTO);
                    break;

                case 3://수정
                    ReviewDTO modifyDTO = modifyMenu();
                    con.modifyReview(modifyDTO);
                    break;
                case 4://삭제
                    int reviewNo = deleteMenu();
                    con.deleteReview(reviewNo);
                    break;
                case -1:
                    return;
                default:
                    System.out.println("잘못된 입력");
                    break;
            }
        }
    }

    private void searchReview() {
        while (true) {
            displayReviewSearchMenu();
            ReviewController con = new ReviewController();
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    int travelNo = searchFromLocMenu();
                    con.searchFromLoc(travelNo);
                    break;
                case 2:
                    String writer = searchFromWriter();
                    con.searchFromWriter(writer);
                    break;
                case 3:
                    int reviewNo = printDetailReview();
                    con.readDetailReview(reviewNo);
                    break;
                case -1:
                    System.out.println("종료");
                    return;
                default:
                    System.out.println("잘못된 입력");
                    break;
            }
        }

    }

    private int printDetailReview() {
        System.out.println("=================================");
        System.out.println("===========리뷰 상세 출력===========");
        System.out.print("출력할 리뷰 번호 입력 >> ");
        int reviewNo = sc.nextInt();
        sc.nextLine();
        return reviewNo;
    }

    private String searchFromWriter() {
        System.out.println("=================================");
        System.out.println("=============리뷰 검색=============");
        System.out.print("작성자 닉네임 입력 >> ");
        return sc.nextLine();
    }

    private int searchFromLocMenu() {
        System.out.println("=================================");
        System.out.println("=============리뷰 검색=============");
        System.out.print("관광지 번호 입력 >> ");
        int travelNo = sc.nextInt();
        sc.nextLine();
        return travelNo;
    }

    private void displayReviewSearchMenu() {
        System.out.println("=================================");
        System.out.println("=============메뉴 선택=============");
        System.out.println("-1. 종료");
        System.out.println("1. 관광지로 리뷰 검색");
        System.out.println("2. 작성자로 리뷰 검색");
        System.out.println("3. 리뷰 번호로 상세 출력");
        System.out.print("입력 >> ");
    }

    private int deleteMenu() {
        System.out.println("=================================");
        System.out.println("===========리뷰 삭제 화면===========");
        System.out.print("삭제할 리뷰 번호 입력 >> ");
        int reviewNo = sc.nextInt();
        sc.nextLine();
        return reviewNo;
    }

    private ReviewDTO modifyMenu() {
        ReviewDTO dto = new ReviewDTO();
        System.out.println("=================================");
        System.out.println("===========리뷰 수정 화면===========");
        System.out.print("수정할 리뷰 번호 입력 >> ");
        dto.setReviewNo(sc.nextInt());
        sc.nextLine();
        ReviewController con = new ReviewController();
        con.readDetailReview(dto.getReviewNo());

        System.out.print("새로운 제목 >> ");
        dto.setReviewTitle(sc.nextLine());
        System.out.print("새로운 내용 >> ");
        dto.setContent(sc.nextLine());
        System.out.print("새 평점 >> ");
        dto.setRate(sc.nextInt());
        sc.nextLine();
        return dto;
    }

    private ReviewDTO insertMenu() {
        ReviewDTO dto = new ReviewDTO();
        dto.setUserNo(UserController.myUserNo);
        System.out.println("=================================");
        System.out.println("===========리뷰 등록 화면===========");
        System.out.print("관광지 번호 입력 >> ");
        dto.setTravelNo(sc.nextInt());
        sc.nextLine();
        System.out.print("리뷰 제목 입력 >> ");
        dto.setReviewTitle(sc.nextLine());
        System.out.print("리뷰 내용 입력 >> ");
        dto.setContent(sc.nextLine());
        System.out.print("평점 입력 >> ");
        dto.setRate(sc.nextInt());
        sc.nextLine();
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
