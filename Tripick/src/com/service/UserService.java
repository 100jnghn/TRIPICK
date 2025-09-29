package com.service;

import com.common.DBConnectionMgr;
import com.model.dto.ReviewDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {
    private final ReviewDTO reviewDTO;
    Connection conn = null;
    DBConnectionMgr dbcp = null;

    public UserService() {
        dbcp = DBConnectionMgr.getInstance();
        if (dbcp.getConnectionCount() == 0) {
            try {
                dbcp.setInitOpenConnections(3);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        reviewDTO = new ReviewDTO();
    }



}
