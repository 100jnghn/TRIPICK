package com.model.dao;

import com.common.DBConnectionMgr;
import com.controller.UserController;
import com.model.dto.UserDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.common.DBConnectionMgr.getInstance;

public class UserDAO {

    // region PROP

    private Properties prop = new Properties();

    // endregion
    // region CTOR

    public UserDAO() {
        try {
            prop.load(UserDAO.class.getResourceAsStream("/query.properties"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // endregion
    // region METHODS

    public int insertUser(Connection conn, UserDTO user) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = "insert into users values (null, ?, ?, ?, ?, now(), null)";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPw());
            pstmt.setString(3, user.getNickname());
            pstmt.setInt(4, user.getAge());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnectionMgr dbcp = getInstance();
            dbcp.freeConnection(pstmt);
        }

        return result;
    }

    public UserDTO selectOneById(Connection conn, String id) {
        UserDTO user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from user where id = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new UserDTO();

                user.setUserNo(rs.getInt("user_no"));
                user.setId(rs.getString("id"));
                user.setPw(rs.getString("pw"));
                user.setNickname(rs.getString("nickname"));
                user.setAge(rs.getInt("age"));
                user.setCreatedAt(rs.getDate("created_at"));
                user.setUpdatedAt(rs.getDate("updated_at"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnectionMgr dbcp = getInstance();
            dbcp.freeConnection(pstmt, rs);
        }

        return user;
    }

    public int updateId(Connection conn, String id) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = "update users set id = ?, updated_at = now() where user_no = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setInt(2, UserController.myUserNo);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnectionMgr dbcp = getInstance();
            dbcp.freeConnection(pstmt);
        }

        return result;
    }

    public int updatePw(Connection conn, String pw) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = "update users set pw = ?, updated_at = now() where user_no = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pw);
            pstmt.setInt(2, UserController.myUserNo);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnectionMgr dbcp = getInstance();
            dbcp.freeConnection(pstmt);
        }

        return result;
    }

    public int updateInfo(Connection conn, String nickname, int age) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = "update users set nickname = ?, age = ?, updated_at = now() where user_no = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nickname);
            pstmt.setInt(2, age);
            pstmt.setInt(3, UserController.myUserNo);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    // endregion

}
