package com.controller;

import com.model.dto.UserDTO;
import com.service.UserService;

public class UserController {

    UserService userService = new UserService();

    // region FIELDS

    /*
     * isLogin : 처음 로그인할 때 false -> true로 변경
     * myUserNo : 로그인 성공하면 입력한 유저의 user_no 저장
     * */
    public static boolean isLogin;
    public static int myUserNo;

    // endregion
    // region METHODS

    // 로그인 기능
    public void login(String id, String pw) {

        // ===== 뷰 연결 ===== //

        try {
            UserDTO user = userService.login(id, pw);

            // 일치하는 아이디 없음
            if (user == null) {

                return;
            }

            // 비밀번호 다름
            if (!pw.equals(user.getPw())) {

                return;
            }

            // 로그인 성공
            isLogin = true;
            myUserNo = user.getUserNo();

        } catch (Exception e) {

            // 에러 메시지
            e.printStackTrace();
        }
    }

    public void signUp(UserDTO user) {

        // ===== 뷰 연결 ===== //

        try {
            int result = userService.signUp(user);

            if (result > 0) {
                // 회원가입 성공

            } else {
                // 회원가입 실패
            }

        } catch (Exception e) {

            // 오류 메시지
            e.printStackTrace();
        }
    }

    public void updateId(String id) {
        if (!isLogin) {

            // 로그인 상태가 아님
            return;
        }

        // ===== 뷰 연결 ===== //

        try {
            int result = userService.updateId(id);

            if (result > 0) {
                // 아이디 변경 성공

            } else {
                // 아이디 변경 실패
            }

        } catch (Exception e) {

            // 오류 메시지
            e.printStackTrace();
        }
    }

    public void updatePw(String pw) {
        if (!isLogin) {

            // 로그인 상태가 아님
            return;
        }

        // ===== 뷰 연결 ===== //

        try {
            int result = userService.updatePw(pw);

            if (result > 0) {
                // 비번 변경 성공

            } else {
                // 비번 변경 실패
            }

        } catch (Exception e) {

            // 오류 메시지
            e.printStackTrace();
        }
    }

    public void updateInfo(String nickname, int age) {
        if (!isLogin) {

            // 로그인 상태가 아님
            return;
        }

        // ===== 뷰 연결 ===== //

        try {
            int result = userService.updateInfo(nickname, age);

            if (result > 0) {
                // 닉네임, 나이 변경 성공

            } else {
                // 변경 실패
            }

        } catch (Exception e) {

            // 오류 메시지
            e.printStackTrace();
        }
    }

    public void deleteUser(String id) {
        if(!isLogin) {

            // 로그인 상태가 아님
            return;
        }

        // ===== 뷰 연결 ===== //
        try {
            int result = userService.deleteUser(id);

            if (result > 0) {

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // endregion

}
