package com.model.dao;

import com.common.DBConnectionMgr;
import com.model.dto.ReviewDTO;
import com.model.dto.TravelDTO;
import com.model.dto.UserDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.common.DBConnectionMgr.getInstance;

public class TravelDAO {

    private Properties prop = new Properties();

    public TravelDAO() {


    }

    //1. 전체 관광지 리스트

    public ArrayList<TravelDTO> TravelList(Connection conn) {

        ArrayList<TravelDTO> list = new ArrayList<TravelDTO>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from Travel order by (sum / count) desc";

        try {

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                TravelDTO traveldto = new TravelDTO();

                traveldto.setTravelNo(rs.getInt("travel_no"));
                traveldto.setDistrict(rs.getString("District"));
                traveldto.setTitle(rs.getString("Title"));
                traveldto.setSum(rs.getFloat("Sum"));
                traveldto.setCount(rs.getInt("Count"));

                list.add(traveldto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBConnectionMgr.getInstance().freeConnection(pstmt, rs);
        }
        return list;
    }


    //2. 권역별 검색 결과
    public ArrayList<TravelDTO> districtList(Connection conn, String district) {

        ArrayList<TravelDTO> list = new ArrayList<TravelDTO>();
        String sql = "select * from Travel where District = ? order by (sum / count) desc";

        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, district);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TravelDTO traveldto = new TravelDTO();

                traveldto.setTravelNo(rs.getInt("travel_no"));
                traveldto.setDistrict(rs.getString("District"));
                traveldto.setTitle(rs.getString("Title"));
                traveldto.setSum(rs.getFloat("Sum"));
                traveldto.setCount(rs.getInt("Count"));

                list.add(traveldto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    //3. 지역명으로 관광지 검색(지역명 -> title)
    public ArrayList<TravelDTO> titleList(Connection conn, String title) {

        ArrayList<TravelDTO> list = new ArrayList<>();
        String sql = "select * from Travel where Title like ? order by (sum / count) desc";

        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TravelDTO traveldto = new TravelDTO();

                traveldto.setTravelNo(rs.getInt("travel_no"));
                traveldto.setDistrict(rs.getString("District"));
                traveldto.setTitle(rs.getString("Title"));
                traveldto.setSum(rs.getFloat("Sum"));
                traveldto.setCount(rs.getInt("Count"));

                list.add(traveldto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    //4. 관광지 번호로 세부 조회 (관광지 번호 : travelNo)
    public TravelDTO traveldetailList(Connection conn, int travelNo) {

        TravelDTO traveldto = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from Travel where travel_no = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, travelNo);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                traveldto = new TravelDTO();
                traveldto.setTravelNo(rs.getInt("travel_no"));
                traveldto.setDistrict(rs.getString("District"));
                traveldto.setTitle(rs.getString("Title"));
                traveldto.setAddress(rs.getString("Address"));
                traveldto.setPhone(rs.getString("Phone"));
                traveldto.setSum(rs.getFloat("Sum"));
                traveldto.setCount(rs.getInt("Count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return traveldto;
    }


    //5. 사용자의 나이를 기준으로 가장 선호하는 여행지 검색
    public ArrayList<TravelDTO> listByUserAge(Connection conn, UserDTO user) {
        ArrayList<TravelDTO> list = new ArrayList<>();
        String sql = "SELECT * \n" +
                "FROM (SELECT a.travel_no\n" +
                "FROM review a\n" +
                "left JOIN USERS b ON b.user_no = a.user_no\n" +
                "WHERE b.age BETWEEN ? AND ?\n" +
                "GROUP BY a.travel_no\n" +
                "ORDER BY sum(a.rate) / count(*) DESC LIMIT 3) AS sub\n" +
                "LEFT JOIN travel t USING(travel_no);";

        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, (user.getAge() / 10)*10);
            pstmt.setInt(2, (user.getAge() / 10)*10 + 9);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TravelDTO traveldto = new TravelDTO();

                traveldto.setTravelNo(rs.getInt("travel_no"));
                traveldto.setDistrict(rs.getString("District"));
                traveldto.setTitle(rs.getString("Title"));
                traveldto.setSum(rs.getFloat("Sum"));
                traveldto.setCount(rs.getInt("Count"));

                list.add(traveldto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //6. 관광지별 최신 리뷰 조회
    public ArrayList<ReviewDTO> getReviewList(Connection conn, TravelDTO place) {
        ArrayList<ReviewDTO> list = new ArrayList<>();
        String sql = "select * from Review where travel_no = ? order by created_at desc limit 3";
        ReviewDTO reviewdto = new ReviewDTO();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, place.getTravelNo());
            ResultSet rs = pstmt.executeQuery();

            do {
                reviewdto.setReviewNo(rs.getInt("review_no"));
                reviewdto.setUserNo(rs.getInt("user_no"));
                reviewdto.setReviewTitle(rs.getString("title"));
                reviewdto.setContent(rs.getString("content"));
                reviewdto.setCreatedAt(rs.getString("created_at"));
                System.out.println(reviewdto+" rs.next\n====");
                list.add(reviewdto);
            } while(rs.next());

            for(ReviewDTO reviewDTO : list) {
                System.out.println(reviewDTO+" in list\n====");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}