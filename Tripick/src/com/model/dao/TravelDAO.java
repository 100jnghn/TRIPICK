package com.model.dao;

import com.common.DBConnectionMgr;
import com.model.dto.TravelDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class TravelDAO {

    private Properties prop = new Properties();

    public TravelDAO() {

        try {

            prop.load(UserDAO.class.getResourceAsStream("/query.properties"));
            System.out.println(prop);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //1. 전체 관광지 리스트

    public ArrayList<TravelDTO> TravelList(Connection conn) {

        ArrayList<TravelDTO> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from Travel order by (sum / count) desc ";

        try {

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                TravelDTO traveldto = new TravelDTO();

                traveldto.setTravelNo(rs.getInt("TravelNo"));
                traveldto.setDistrict(rs.getString("District"));
                traveldto.setTitle(rs.getString("Title"));
                traveldto.setSum(rs.getFloat("Sum"));
                traveldto.setCount(rs.getInt("Count"));

                list.add(traveldto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DBConnectionMgr.getInstance().freeConnection(pstmt, rs);
        }
        return list;
    }



    //2. 권역별 검색 결과
    public ArrayList<TravelDTO> districtList(Connection conn, String district) {

        ArrayList<TravelDTO> list = new ArrayList<>();
        String sql = "select * from Travel where District = ? order by (sum / count) desc";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, district);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                TravelDTO traveldto = new TravelDTO();

                traveldto.setTravelNo(rs.getInt("TravelNo"));
                traveldto.setDistrict(rs.getString("District"));
                traveldto.setTitle(rs.getString("Title"));
                traveldto.setSum(rs.getFloat("Sum"));
                traveldto.setCount(rs.getInt("Count"));

                list.add(traveldto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionMgr.getInstance().freeConnection(pstmt, rs);
        }
        return list;
    }


    //3. 지역명으로 관광지 검색(지역명 -> title)
    // 종원님 구현 예정

//    public ArrayList<TravelDTO> titleList(Connection conn, String district, String title) {
//
//        ArrayList<TravelDTO> list = new ArrayList<>();
//        String sql = "select * from Travel where Title = ? order by (sum / count) desc)";
//
//        try {
//
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, title);
//
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                TravelDTO traveldto = new TravelDTO();
//
//                traveldto.setTravelNo(rs.getInt("TravelNo"));
//                traveldto.setDistrict(rs.getString("District"));
//                traveldto.setTitle(rs.getString("Title"));
//                traveldto.setSum(rs.getFloat("Sum"));
//                traveldto.setCount(rs.getInt("Count"));
//
//                list.add(traveldto);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }


    //4. 관광지 번호로 세부 조회 (관광지 번호 : travelNo)
    public TravelDTO traveldetailList(Connection conn, int travelNo) {

        TravelDTO traveldto = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from Travel where TravelNo = ?";

        try {

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, travelNo);

            rs = pstmt.executeQuery();

            if (rs.next()){
                traveldto = new TravelDTO();

                traveldto.setTravelNo(rs.getInt("TravelNo"));
                traveldto.setDistrict(rs.getString("District"));
                traveldto.setTitle(rs.getString("Title"));
                traveldto.setDesc(rs.getString("Desc"));
                traveldto.setAddress(rs.getString("Address"));
                traveldto.setPhone(rs.getString("Phone"));
                traveldto.setSum(rs.getFloat("Sum"));
                traveldto.setCount(rs.getInt("Count"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnectionMgr.getInstance().freeConnection(pstmt, rs);
        }
        return traveldto;
    }

}
