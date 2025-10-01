package com.view;

import com.controller.TravelControllerJW;
import com.model.dto.ReviewDTO;
import com.model.dto.TravelDTO;

import java.util.ArrayList;
import java.util.Scanner;

public class TempTravelViewJW {
    private Scanner sc = new Scanner(System.in);
    private TravelControllerJW tController = null;

    public TempTravelViewJW() {
        System.out.println();
    }

    public void inputTravelMenu() {
        String choice;
        tController = new TravelControllerJW();
        while (true) {
            System.out.println("\n========== Tripick ==========");
            System.out.println("1. 전체 관광지 리스트");
            System.out.println("2. 권역별 검색");
            System.out.println("3. 지역별 검색");
            System.out.println("4. 회원님과 비슷한 나이의 유저들 선호지역 검색");
            System.out.println("-1. 메인 메뉴");
            System.out.println("===============================");
            System.out.print("원하시는 서비스의 번호를 입력하세요: ");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    tController.showTravelList();
                    break;
                case "2":
                    tController.districtSearch(inputDistrict());
                    break;
                case "3":
                    tController.CitySearch(inputCity());
                    break;
                case "4":
                    tController.listByAge();
                    break;
                case "-1":
                    return;
                default:
                    displayInvalidInput();
            }
        }
    }

    public void displayAllTravelList() {
        System.out.println("\n========== 전체 조회 결과 ==========");
        System.out.println("No \t District \t Title \t Rate");
        System.out.println("---------------------------------");

    }

    public void displayTravelList(ArrayList<TravelDTO> list) {
        tController = new TravelControllerJW();
        int showIndex = 0;
        String choice;
        TravelDTO place = null;
        do {
            for (int i = 0; i < 9; i++) {
                if (list.size() == showIndex + i) break;
                place = list.get(showIndex + i);
                System.out.println((i + 1) + " \t " + place.getDistrict()
                        + " \t " + place.getTitle() + " \t " + Rate(place));
            }
            System.out.println("-1. 뒤로 가기");
            System.out.println("n. 다음 목록");
            System.out.print("세부 조회를 원하시면 해당 번호 입력: ");
            choice = sc.nextLine();
            switch (choice) {
                case "1", "2", "3", "4", "5",
                     "6", "7", "8", "9" -> {
                    if (showIndex + Integer.parseInt(choice) >= list.size() + 1)
                        displayInvalidInput();
                    else {
                        tController.showTravelDetail(list.get(showIndex + Integer.parseInt(choice) - 1).getTravelNo());
                    }
                }
                case "-1" -> {
                    return;
                }
                case "n" -> {
                    showIndex += 9;
                    if (showIndex >= list.size())
                        System.out.println("마지막 리스트입니다.");
                }
                default -> {
                    displayInvalidInput();
                    return;
                }
            }
        } while (showIndex < list.size());
    }

    //권역별 검색 결과 출력
    public void displayDistrictResult(ArrayList<TravelDTO> list, String district) {
        System.out.println("\n========== < " + district + " > ==========");
        System.out.println("No \t District \t Title \t Rate");
        System.out.println("---------------------------------");
        displayTravelList(list);
    }

    //권역 입력 받기
    public String inputDistrict() {
        System.out.println("검색하실 권역을 입력하세요");
        return sc.nextLine();
    }

    //지역 입력 받기
    public String inputCity() {
        System.out.println("검색하실 권역을 입력하세요");
        return sc.nextLine();
    }

    //세부 조회 출력
    public void displayTravelDetail(TravelDTO travelDTO) {
        tController = new TravelControllerJW();
        System.out.println("\n========== 관광지 상세 정보 ==========");
        System.out.println("지역 : " + travelDTO.getDistrict());
        System.out.println("장소명 : " + travelDTO.getTitle());
        System.out.println("주소 : " + (travelDTO.getAddress() != null ? travelDTO.getAddress() : "정보 없음"));
        System.out.println("연락처 : " + (travelDTO.getPhone() != null ? travelDTO.getPhone() : "정보 없음"));
        System.out.println("평점 : " + Rate(travelDTO) + " / 5.0");

        if (travelDTO.getDesc() != null && !travelDTO.getDesc().isEmpty()) {
            System.out.println("\n관광지 설명 : ");
            System.out.println("---------------------------------");
            System.out.println(travelDTO.getDesc());
            System.out.println("---------------------------------");
        }
        tController.showReviews(travelDTO);
    }

    //리뷰 리스트 출력
    public void displayReview(ArrayList<ReviewDTO> reviewList) {
        for (ReviewDTO i : reviewList) {
            System.out.println(i);
        }
    }

    //지역 검색 결과 출력
    public void displayCityResult(ArrayList<TravelDTO> list, String City) {
        System.out.println("\n========== < " + City + " > ==========");
        System.out.println("No \t District \t Title \t Rate");
        System.out.println("---------------------------------");
        displayTravelList(list);
    }

    //나이 구별 검색 결과 출력
    public void displayAgeList(ArrayList<TravelDTO> list) {
        System.out.println("회원님과 비슷한 나이의 유저들이 선호하는 관광지입니다.");
        System.out.println("No \t District \t Title \t Rate");
        System.out.println("---------------------------------");
        displayTravelList(list);
    }

    //조회된 데이터 없을 때
    public void displayNoData() {
        System.out.println("등록된 관광지가 없습니다.");
    }

    //권역별 조회 실패
    public void displaySearchFailed(String district) {
        System.out.println(district + "권역의 관광지가 없습니다.");
    }

    //지역별 조회 실패
    public void displayTitleSearchFailed(String district) {
        System.out.println(district + "지역의 관광지가 없습니다.");
    }

    //평점계산
    private float Rate(TravelDTO travelDTO) {
        if (travelDTO.getCount() == 0) {
            return 0;
        }
        float rate = travelDTO.getSum() / travelDTO.getCount();
        return Float.parseFloat(String.format("%.1f", rate));
    }

    //잘못된 입력
    public void displayInvalidInput() {
        System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
    }

    //상세조회 실패
    public void displayDetailNotFound() {
        System.out.println("해당 관광지를 찾을 수 없습니다.");
    }
}
