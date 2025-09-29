package org.dao;

import org.common.JDBCConnect;
import org.dto.TravelDTO;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TravelDAO {
    PreparedStatement pstmt = null;

    public int insert(TravelDTO dto) {
        Connection conn = JDBCConnect.getConnection();
        int result = 0;

        try {
            String sql = "insert into TRAVEL values(?, ?, ?, ?, ?, ?, 0, 0)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, dto.getNo());
            pstmt.setString(2, dto.getDistrict());
            pstmt.setString(3, dto.getTitle());
            pstmt.setString(4, dto.getDescription());
            pstmt.setString(5, dto.getAddress());
            pstmt.setString(6, dto.getPhone());

            result = pstmt.executeUpdate();

            if (result > 0) {
            } else {
                conn.rollback();
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCConnect.close(pstmt);
        }
    }
}
