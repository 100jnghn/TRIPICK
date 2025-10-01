package com.view;

import com.model.dto.TravelDTO;

import java.util.ArrayList;
import java.util.Scanner;

public class TravelView {
    private Scanner sc = new Scanner(System.in);
    private TravelDTO travelDTO;

    //
    public int inputTravelMenu() {
        System.out.println("\n========== Tripick ==========");
            System.out.println("1. 전체 관광지 리스트");
            System.out.println("2. 권역별 검색");
            System.out.println("3. 지역별 검색");
            System.out.println("4. " + "nickname" + "님과 비슷한 나이의 유저들 선호지역 검색");
            System.out.println("0. 메인 메뉴");
            System.out.println("===============================");
            System.out.println("원하시는 서비스의 번호를 입력하시오");

            return Integer.parseInt(sc.next());
    }



    //권역별 검색
    public String inputDistrict() {
        System.out.println("\n========== 권역별 검색 ==========");
        System.out.println("검색하실 권역을 입력해주세요 : ");

        return sc.next();
    }

    //뒤로가기
    public int inputBack() {
        System.out.println("\n0. 뒤로가기");
        System.out.println("세부 조회를 원하시면 해당 번호 입력");
        System.out.println("입력 : ");

       String input = sc.nextLine();

        if(input.equals("0")){
            return 0;
        }else{
            return Integer.parseInt(input);
        }
    }

    //다음페이지 엔터
    public void inputEnter(){
        System.out.println("\n검색 화면으로 넘어가려면 엔터 입력");
        sc.nextLine();
    }

    //전체 관광지 리스트
    public void displayTravelList(ArrayList<TravelDTO>list){
        System.out.println("\n========== 전체 관광지 리스트 ==========");
        System.out.println("No \t District \t Title \t Rate");
        System.out.println("---------------------------------");

        for (TravelDTO travelDTO : list) {
            System.out.println(travelDTO.getTravelNo() + "\t" +
                    travelDTO.getDistrict() + "\t" +
                    travelDTO.getTitle() + "\t\t" +
                    Rate(travelDTO));
    }
}

    //권역별 검색 결과 출력
    public void displayDistrictResult(ArrayList<TravelDTO>list, String district){
        System.out.println("\n========== < " + district + " > ==========");
        System.out.println("No \t District \t Title \t Rate");
        System.out.println("---------------------------------");

        for (TravelDTO travelDTO : list) {
            System.out.println(travelDTO.getTravelNo() + "\t" +
                    travelDTO.getDistrict() + "\t" +
                    travelDTO.getTitle() + "\t\t" +
                    Rate(travelDTO));
        }
    }




    //세부 조회 출력
    public void displayTravelDetail(TravelDTO travelDTO){
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
    }


    public void displayNoData(){
        System.out.println("등록된 관광지가 없습니다.");}

    public void displaySearchFailed(String district){
        System.out.println(district + "권역의 관광지가 없습니다.");
    }



    //평점계산
    private float Rate(TravelDTO travelDTO) {
        if (travelDTO.getCount()== 0){
            return 0;
        }
        float rate = travelDTO.getSum() / travelDTO.getCount();
        return Float.parseFloat(String.format("%.1f", rate));
    }

    public void displayInvalidInput() {
        System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
    }

    public void displayDetailNotFound() {
        System.out.println("해당 관광지를 찾을 수 없습니다.");
    }

}
