package com.service;

import com.common.DBConnectionMgr;
import com.model.dao.TravelDAO;
import com.model.dto.TravelDTO;

import java.sql.Connection;
import java.util.ArrayList;

public class TravelService {

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


}
