package com.controller;

import com.model.dto.TravelDTO;
import com.service.TravelService;
import com.view.TempTravelView;


import java.util.ArrayList;
import java.util.Scanner;

public class TravelController {

    private Scanner sc = new Scanner(System.in);
    private TravelService travelService = new TravelService();
    private TempTravelView view = new TempTravelView();


    //1. 전체 관광지 리스트
    public void showTravelList(){
        ArrayList<TravelDTO> list = travelService.travelList();

        if(list.isEmpty()){
            view.displayNoData();
            return;
        }

        view.displayTravelList(list);

        int choice = view.inputBack();

        if(choice == 0){
            return;
        }else if(choice >= 0){
            showTavelDetail(choice);
        }
    }


    //2. 권역별 검색
    public void districtSearch(){
        String district = view.inputDistrict();

        if(district.isEmpty()){
            view.displayInvalidInput();
            return;
        }

        ArrayList<TravelDTO> list = travelService.districtList(district);

        if(list.isEmpty()){
            view.displaySearchFailed(district);
            return;
        }
        view.displayDistrictResult(list, district);

        int choice = view.inputBack();

        if(choice == 0){
            return;
        }else if(choice >= 0){
            showTavelDetail(choice);
        }
    }

    //3. 세부 조회
    public void showTavelDetail(int travelNo){
        TravelDTO travelDTO = travelService.traveldetailList(travelNo);

        if(travelDTO == null){
            view.displayDetailNotFound();
            return;
        }

        view.displayTravelDetail(travelDTO);
        view.inputBack();
    }


    //MAIN
//    public void travelMenu() {
//
//        TravelController travelController = new TravelController();
//        TempTravelView tempTravelView = new TempTravelView();
//
//        while (true) {
////            int choice = view.inputMenu();
//
////            switch (choice) {
////                case 1:
////                    travelController.showTravelList();
////                    break;
////                case 2:
////                    travelController.districtSearch();
////                    break;
////
//////            case 3: -> 종원님 구현 예정
//////
//////                 break;
////                case 0:
////                    view.inputBack;
////                    break;
////                default:
////                    view.displayInvalidInput();
////            }
//        }
//    }
}
