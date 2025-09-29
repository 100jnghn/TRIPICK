package com.service;

import com.common.DBConnectionMgr;
import com.model.dao.ReviewDAO;
import com.model.dto.ReviewDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewService {

    private final ReviewDAO reviewDAO;
    Connection conn = null;
    DBConnectionMgr dbcp = null;

    public ReviewService() {
        dbcp = DBConnectionMgr.getInstance();
        if (dbcp.getConnectionCount() == 0) {
            try {
                dbcp.setInitOpenConnections(3);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        reviewDAO = new ReviewDAO();
    }

    public ArrayList<ReviewDTO> searchFromLoc(int travelNo) {
        ArrayList<ReviewDTO> list = new ArrayList<>();
        try {
            conn = dbcp.getConnection();
            list = reviewDAO.searchFromLoc(conn, travelNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }
        return list;
    }

    public ArrayList<ReviewDTO> searchFromWriter(String nickName) {
        ArrayList<ReviewDTO> list = new ArrayList<>();
        try {
            conn = dbcp.getConnection();
            list = reviewDAO.searchFromWriter(conn, nickName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }
        return list;
    }


    public ReviewDTO readDetailReview(int reviewNo) {
        ReviewDTO reviewDTO = null;
        try {
            conn = dbcp.getConnection();
            reviewDTO = reviewDAO.readDetailReview(conn, reviewNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }
        return reviewDTO;
    }

    public int insertReview(ReviewDTO dto) {
        int rs = 0;
        try {
            conn = dbcp.getConnection();
            rs = reviewDAO.insertReview(conn, dto);
            reviewDAO.updateRate(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return rs;
    }

    public int modifyReview(ReviewDTO dto) {
        int rs = 0;
        try {
            conn = dbcp.getConnection();
            rs = reviewDAO.modifyReview(conn, dto);
            reviewDAO.updateRate(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }
        return rs;
    }


    public int deleteReview(int reviewNo) {
        int rs = 0;
        try {
            conn = dbcp.getConnection();
            rs = reviewDAO.deleteReview(conn, reviewNo);
            reviewDAO.updateRate(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }
        return rs;
    }
}
