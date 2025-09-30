package com.service;

import com.common.DBConnectionMgr;
import com.model.dao.UserDAO;
import com.model.dto.UserDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {

    // region PROPS

    private final UserDAO userDAO;
    Connection conn = null;
    DBConnectionMgr dbcp = null;

    // endregion

    // region CTOR

    public UserService() {
        dbcp = DBConnectionMgr.getInstance();

        if (dbcp.getConnectionCount() == 0) {
            try {
                dbcp.setInitOpenConnections(10);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        userDAO = new UserDAO();
    }

    // endregion
    // region METHODS

    // 로그인 기능
    public UserDTO login(String id, String pw) {

        UserDTO user = null;

        try {
            conn = dbcp.getConnection();
            user = userDAO.selectOneById(conn, id);

        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return user;
    }

    // 회원가입 기능
    public int signUp(UserDTO user) {

        int result = 0;

        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false);

            result = userDAO.insertUser(conn, user);

            // 회원가입 dao 성공
            if (result > 0) conn.commit();
            else conn.rollback();


        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return result;
    }

    // 아이디 값으로 한 행 읽어오기
    public UserDTO selectOneById(String id) {
        UserDTO user = null;

        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false);

            user = userDAO.selectOneById(conn, id);

        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return user;
    }

    // user_no값으로 한 행 읽어오기
    public UserDTO selectOneByNo(int userNo) {
        UserDTO user = null;

        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false);

            user = userDAO.selectOneByNo(conn, userNo);

        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return user;
    }

    // ID 변경
    public int updateId(String id) {

        int result = 0;

        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false);

            result = userDAO.updateId(conn, id);

            if (result > 0) conn.commit();
            else conn.rollback();


        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return result;
    }

    // PW 변경
    public int updatePw(String pw) {

        int result = 0;

        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false);

            result = userDAO.updatePw(conn, pw);

            if (result > 0) conn.commit();
            else conn.rollback();


        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return result;
    }

    // 닉네임, 나이 변경
    public int updateInfo(String nickname, int age) {

        int result = 0;

        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false);

            result = userDAO.updateInfo(conn, nickname, age);

            if (result > 0) conn.commit();
            else conn.rollback();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return result;
    }

    public int deleteUser(String id) {
        int result = 0;

        try {
            conn = dbcp.getConnection();
            conn.setAutoCommit(false);

            result = userDAO.deleteUser(conn, id);

            if (result > 0) conn.commit();
            else conn.rollback();

        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                dbcp.freeConnection(conn);
            }
        }

        return result;
    }

    // endregion

}
