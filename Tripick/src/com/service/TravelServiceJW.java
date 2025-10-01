package com.service;

import com.common.DBConnectionMgr;
import com.model.dao.TravelDAO;
import com.model.dao.UserDAO;
import com.model.dto.ReviewDTO;
import com.model.dto.TravelDTO;
import com.model.dto.UserDTO;

import java.sql.Connection;
import java.util.ArrayList;

import static com.controller.UserController.myUserNo;

public class TravelServiceJW {

    private TravelDAO travelDAO = new TravelDAO();
    private DBConnectionMgr pool = DBConnectionMgr.getInstance();
    Connection conn = null;

    //1. 전체 관광지 조회
    //return 전체 관광지 리스트

    public ArrayList<TravelDTO> travelList() {

        ArrayList<TravelDTO> list = null;

        try {

            conn = pool.getConnection();
            list = travelDAO.TravelList(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(conn != null){
                pool.freeConnection(conn);
            }
        }
        return list;
    }


    //2. 권역별 조회
    //param : district
    //return 권역별 관광지 리스트
    public ArrayList<TravelDTO> districtList(String district) {
        ArrayList<TravelDTO> list = null;

        try {

            conn = pool.getConnection();
            list = travelDAO.districtList(conn, district);

            if (list == null) {
                list = new ArrayList<>();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                pool.freeConnection(conn);
            }
        }
        return list;
    }

    //3.관광지 세부 조회
    //param : travelNo
    //return 관광지 상세 정보
    public TravelDTO traveldetailList(int travelNo) {
        TravelDTO travelDTO = null;

        try {

            conn = pool.getConnection();
            travelDTO = travelDAO.traveldetailList(conn, travelNo);

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(conn != null){
                pool.freeConnection(conn);
            }
        }
        return travelDTO;
    }

    //4. 지역별 조회
    //param : title
    //return 지역별 관광지 리스트
    public ArrayList<TravelDTO> titleList(String title) {
        ArrayList<TravelDTO> list = null;

        try {

            conn = pool.getConnection();
            list = travelDAO.titleList(conn, title);

            if (list == null) {
                list = new ArrayList<>();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                pool.freeConnection(conn);
            }
        }
        return list;
    }

    //리뷰 목록 조회
    public ArrayList<ReviewDTO> reviewList(TravelDTO travelDTO) {
        ArrayList<ReviewDTO> list = new ArrayList<>();
        TravelDAO travelDAO = new TravelDAO();
        try {
            conn = pool.getConnection();
            list = travelDAO.getReviewList(conn, travelDTO);
            for(ReviewDTO reviewDTO : list)
                System.out.println(reviewDTO);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                pool.freeConnection(conn);
            }
        }
        return list;
    }

    //나이대로 관광지 검색
    public ArrayList<TravelDTO> listByAge() {
        ArrayList<TravelDTO> list = null;
        UserDAO dao = new UserDAO();
        try {
            conn = pool.getConnection();
            UserDTO user = dao.selectOneByNo(conn, myUserNo);
            list = travelDAO.listByUserAge(conn, user);

            if (list == null) {
                list = new ArrayList<>();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                pool.freeConnection(conn);
            }
        }
        return list;
    }
}
