package com.controller;

import com.model.dto.ReviewDTO;
import com.model.dto.TravelDTO;
import com.service.TravelServiceJW;
import com.view.TravelViewJW;

import java.util.ArrayList;

public class TravelControllerJW {

    private TravelServiceJW travelService = new TravelServiceJW();
    private TravelViewJW view = new TravelViewJW();

    //1. 전체 관광지 리스트
    public void showTravelList() {
        ArrayList<TravelDTO> list = travelService.travelList();

        if (list.isEmpty()) {
            view.displayNoData();
            return;
        }

        view.displayAllTravelList();
        view.displayTravelList(list);
    }


    //2. 권역별 검색
    public void districtSearch(String district) {
        if (district.isEmpty()) {
            view.displayInvalidInput();
            return;
        }

        ArrayList<TravelDTO> list = travelService.districtList(district);

        if (list.isEmpty()) {
            view.displaySearchFailed(district);
            return;
        }
        view.displayDistrictResult(list, district);
    }

    //3. 세부 조회
    public void showTravelDetail(int travelNo) {
        TravelDTO travelDTO = travelService.traveldetailList(travelNo);

        if (travelDTO == null) {
            view.displayDetailNotFound();
            return;
        }

        view.displayTravelDetail(travelDTO);
    }

    //관광지별 최신 리뷰 조회
    public void showReviews(TravelDTO travelDTO) {
        ArrayList<ReviewDTO> reviewList  = travelService.reviewList(travelDTO);
        if (reviewList.isEmpty()) {
            view.displayNoData();
        }
        else{
            view.displayReview(reviewList);
        }
    }

    //지역별 최신 리뷰 조회
    public void CitySearch(String title) {
        if (title.isEmpty()) {
            view.displayInvalidInput();
            return;
        }

        ArrayList<TravelDTO> list = travelService.titleList(title);

        if (list.isEmpty()) {
            view.displayTitleSearchFailed(title);
            return;
        }
        view.displayCityResult(list, title);
    }

    public void listByAge() {
        ArrayList<TravelDTO> list = travelService.listByAge();
        if(list.isEmpty()) {
            view.displayNoData();
            return;
        }
        view.displayTravelList(list);

    }
}
