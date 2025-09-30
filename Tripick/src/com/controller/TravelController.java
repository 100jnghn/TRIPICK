package com.controller;

import com.model.dto.TravelDTO;
import com.service.TravelService;


import java.util.ArrayList;
import java.util.Scanner;

public class TravelController {

    private Scanner sc = new Scanner(System.in);
    private TravelService travelService = new TravelService();
//    private TravelView view = new TravelView();


    public void travelMenu() {
        while (true) {
//            int choice = view.inputMenu();

//            switch (choice) {
//                case 1:
//                    allTravelList();
//                    break;
//                case 2:
//                    districtSearch();
//                    break;
//
////            case 3: -> 종원님 구현 예정
////               titleSearch();
////                 break;
//                case 0:
//                    System.out.println("뒤로가기");
//                    break;
//                default:
//                    System.out.println("다시 입력해주세요.");
//            }
        }
    }

    //1. 전체 관광지 리스트
    private void allTravelList() {
        ArrayList<TravelDTO> list = travelService.travelList();

        if (list.isEmpty()) {
//            view.displayNoData();
            return;
        }
//        view.diplayTravelList(list);

        String input = sc.nextLine();

        if (input.equals("0")) {
            return;
        }else{
            int travelNo = Integer.parseInt(input);
            showTavelDetail(travelNo);
            allTravelList();
        }
    }

    //2. 권역별 검색
    private void districtSearch() {

//        String district = view.inputDistrict();

//        if (district.isEmpty()) {
////            view.displayinput();
//            return;
//        }
    }

//    ArrayList<TravelDTO> list = travelService.districtList(district);
//    if(list.isEmpty()){
////        view.displaySearchFailed(districtSearch());
//        return;
//    }

//    view.displayDistrictResult(list, district);

//    String input = view.inputMenuChoice();

//    if(input.equals("0")){
//        return;
//    }else{
//        int travelNo = Integer.parseInt(input);
//        showTavelDetail(travelNo);
//        districtSearch();
//    }


    //세부 조회
    private void showTavelDetail(int travelNo) {
        TravelDTO travelDTO = travelService.traveldetailList(travelNo);

        if (travelDTO != null) {
//            view.displayTravelDetail(travelDTO);
        }
    }



}
