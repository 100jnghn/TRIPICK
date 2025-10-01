package com.controller;

import com.Auth.LoginAccount;
import com.model.dto.UserDTO;
import com.service.UserService;
import com.view.StartView;

public class UserController {


    // region Service

    UserService userService = new UserService();

    // endregion
    // region METHODS

    // 로그인 기능
    public void login(String id, String pw) {

        // ===== 뷰 연결 ===== //
        StartView view = new StartView();

        try {
            UserDTO user = userService.login(id, pw);

            // 일치하는 아이디 없음
            if (user == null) {
                view.displayMessage("존재하지 않는 아이디입니다.");
                return;
            }

            // 비밀번호 다름
            if (!pw.equals(user.getPw())) {
                view.displayMessage("비밀번호가 일치하지 않습니다.");
                return;
            }

            // 로그인 성공
            // 싱글톤 로그인 인스턴스 값 수정
            LoginAccount.getInstance().setLogin(true);
            LoginAccount.getInstance().setUserId(user.getId());
            LoginAccount.getInstance().setUserNo(user.getUserNo());

            view.displayMessage("로그인 성공!");
            view.displayMessage("=======로그인 정보=======");
            selectOneById(id);

        } catch (Exception e) {

            // 에러 메시지
            e.printStackTrace();
        }
    }

    public void signUp(UserDTO user) {

        // ===== 뷰 연결 ===== //
        StartView view = new StartView();

        try {
            int result = userService.signUp(user);

            // 회원가입 성공
            if (result > 0) {
                view.displayMessage("회원가입에 성공했습니다.");
                view.displayMessage("=======회원 정보=======");
                selectOneById(user.getId());

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

    public void selectOneByNo(int userNo) {
        try {
            UserDTO user = userService.selectOneByNo(userNo);

            if (user != null) {
                // 선택된 유저 없음
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void updateId(String id) {

        // ===== 뷰 연결 ===== //
        StartView view = new StartView();

        try {
            int result = userService.updateId(id);

            if (result > 0) {
                // 아이디 변경 성공
                // 싱글톤 인스턴스 값 수정
                LoginAccount.getInstance().setUserId(id);

                view.displayMessage("아이디가 변경되었습니다.");
                view.displayMessage("=======회원 정보=======");
                selectOneById(id);

            } else {
                view.displayMessage("사용 불가능한 아이디입니다.");
            }

        } catch (Exception e) {

            // 오류 메시지
            e.printStackTrace();
        }
    }

    public void updatePw(String pw) {

        // ===== 뷰 연결 ===== //
        StartView view = new StartView();

        try {
            int result = userService.updatePw(pw);

            // 비번 변경 성공
            if (result > 0) {
                selectOneById(LoginAccount.getInstance().getUserId());
                view.displayMessage("비밀번호가 변경되었습니다.");

            } else {
                // 비번 변경 실패
                view.displayMessage("비밀번호 변경에 실패했습니다.");
            }

        } catch (Exception e) {

            // 오류 메시지
            e.printStackTrace();
        }
    }

    public void updateInfo(String nickname, int age) {

        // ===== 뷰 연결 ===== //
        StartView view = new StartView();

        try {
            int result = userService.updateInfo(nickname, age);

            if (result > 0) {
                // 닉네임, 나이 변경 성공
                view.displayMessage("회원 정보 변경에 성공했습니다.");
                view.displayMessage("=======회원 정보=======");
                selectOneById(LoginAccount.getInstance().getUserId());

            } else {
                // 변경 실패
                view.displayMessage("회원 정보 변경에 실패했습니다.");
            }

        } catch (Exception e) {

            // 오류 메시지
            e.printStackTrace();
        }
    }

    public void deleteUser(String id) {

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
