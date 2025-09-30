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
    public static String myUserId;

    // endregion
    // region METHODS

    // 로그인 기능
    public void login(String id, String pw) {

        // ===== 뷰 연결 ===== //

        try {
            UserDTO user = userService.login(id, pw);

            // 일치하는 아이디 없음
            if (user == null) {

                System.out.println("아이디 없음");
                return;
            }

            // 비밀번호 다름
            if (!pw.equals(user.getPw())) {

                System.out.println("비번 다름");
                return;
            }

            // 로그인 성공
            isLogin = true;
            myUserNo = user.getUserNo();
            myUserId = user.getId();
            selectOneById(id);
            System.out.println("로그인 성공");

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
                selectOneById(user.getId());
                System.out.println("회원가입 성공");

            } else {
                // 회원가입 실패
                System.out.println("회원가입 실패");
            }

        } catch (Exception e) {

            // 오류 메시지
            e.printStackTrace();
        }
    }

    public void selectOneById(String id) {

        try {
            UserDTO user = userService.selectOneById(id);

            if (user != null) {
                // 유저 선택 성공
                System.out.println(user);

            } else {
                // 유저 선택 실패

            }
        } catch (Exception e) {
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
                this.myUserId = id;

                selectOneById(id);

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
                selectOneById(this.myUserId);

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
                selectOneById(this.myUserId);

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
                // 회원 정보 삭제 완료

            } else {
                // 변경 실패
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // endregion

}
