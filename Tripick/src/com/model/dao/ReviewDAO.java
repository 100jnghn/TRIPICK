package com.model.dao;

import com.common.DBConnectionMgr;
import com.model.dto.ReviewDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewDAO {

    public ArrayList<ReviewDTO> searchFromLoc(Connection conn, int travelNo) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ReviewDTO> dtoList = new ArrayList<ReviewDTO>();

        try {
            String sql = "select r.review_no, u.nickname, r.rate, r.created_at, r.updated_at from review r join users u ON r.user_no = u.user_no where travel_no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, travelNo);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ReviewDTO dto = new ReviewDTO();
                dto.setReviewNo(rs.getInt("review_no"));
                dto.setUserNickName(rs.getString("nickname"));
                dto.setRate(rs.getInt("rate"));
                dto.setCreatedAt(rs.getString("created_at"));
                dto.setUpdatedAt(rs.getString("updated_at"));
                dtoList.add(dto);
            }

            return dtoList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DBConnectionMgr.getInstance().freeConnection(pstmt, rs);
        }
    }

    public ArrayList<ReviewDTO> searchFromWriter(Connection conn, String nickName) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ReviewDTO> dtoList = new ArrayList<ReviewDTO>();
            String sql = "select r.review_no, t.title as travel_title, r.title as review_title, r.rate, r.created_at, r.updated_at\n" +
                    "from review r\n" +
                    "    join travel t on r.travel_no = t.travel_no\n" +
                    "    JOIN users u on r.user_no = u.user_no\n" +
                    "where u.nickname = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nickName);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ReviewDTO dto = new ReviewDTO();
                dto.setReviewNo(rs.getInt("review_no"));
                dto.setReviewTitle(rs.getString("review_title"));
                dto.setTravelTitle(rs.getString("travel_title"));
                dto.setRate(rs.getInt("rate"));
                dto.setCreatedAt(rs.getString("created_at"));
                dto.setUpdatedAt(rs.getString("updated_at"));
                dtoList.add(dto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnectionMgr.getInstance().freeConnection(pstmt, rs);
        }
        return dtoList;
    }

    public ReviewDTO readDetailReview(Connection conn, int reviewNo) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ReviewDTO dto = new ReviewDTO();
        String sql = "select u.*, r.review_no, r.title as review_title, t.title as travel_title, r.content, r.rate, r.created_at, r.updated_at from review r join users u using(user_no) join travel t using (travel_no) where review_no=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reviewNo);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dto.setReviewNo(rs.getInt("review_no"));
                dto.setUserNickName(rs.getString("nickname"));
                dto.setReviewTitle(rs.getString("review_title"));
                dto.setTravelTitle(rs.getString("travel_title"));
                dto.setContent(rs.getString("content"));
                dto.setRate(rs.getInt("rate"));
                dto.setCreatedAt(rs.getString("created_at"));
                dto.setUpdatedAt(rs.getString("updated_at"));
            }

            return dto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnectionMgr.getInstance().freeConnection(pstmt, rs);
        }
    }

    public int insertReview(Connection conn, ReviewDTO dto) {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            String sql = "insert into Review values(null, ?, ?, ?, ?, ?, now(), null)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dto.getUserNo());
            pstmt.setInt(2, dto.getTravelNo());
            pstmt.setString(3, dto.getReviewTitle());
            pstmt.setString(4, dto.getContent());
            pstmt.setInt(5, dto.getRate());

            result = pstmt.executeUpdate();

            if(result > 0) {
                conn.commit();
            }else  {
                conn.rollback();
            }

            return result;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DBConnectionMgr.getInstance().freeConnection(pstmt);
        }
    }

    public int modifyReview(Connection conn, ReviewDTO dto) {
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String sql = "update Review set title=?, content=?, rate=? where review_no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getReviewTitle());
            pstmt.setString(2, dto.getContent());
            pstmt.setInt(3, dto.getRate());
            pstmt.setInt(4, dto.getReviewNo());
            result = pstmt.executeUpdate();
            if(result > 0) {
                conn.commit();
            }else   {
                conn.rollback();
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DBConnectionMgr.getInstance().freeConnection(pstmt);
        }
    }

    public int deleteReview(Connection conn, int reviewNo) {
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String sql = "delete from Review where review_no=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reviewNo);

            result = pstmt.executeUpdate();
            if(result > 0) {
                conn.commit();
            }else   {
                conn.rollback();
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DBConnectionMgr.getInstance().freeConnection(pstmt);
        }
    }

    public void updateRate(Connection conn) {
        PreparedStatement pstmt = null;
        String sql = "UPDATE travel AS t LEFT JOIN (SELECT r.travel_no, SUM(r.rate) AS sum_rate, COUNT( *)AS cnt FROM review AS r GROUP BY r.travel_no)AS j ON j.travel_no = t.travel_no SET t.sum = COALESCE(j.sum_rate, 0), t.count = COALESCE(j.cnt, 0)";
        try {
            pstmt = conn.prepareStatement(sql);
            int i = pstmt.executeUpdate();
            if (i < 1) {
                System.out.println("update TRAVEL error");
                conn.rollback();
            } else {
                System.out.println("update TRAVEL success");
                conn.commit();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnectionMgr.getInstance().freeConnection(pstmt);
        }
    }


}
