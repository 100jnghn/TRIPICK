package com.view;

import com.controller.UserController;
import com.model.dto.UserDTO;

import java.util.Scanner;

// 로그인 - 회원가입 - 유저 정보 수정 진행 뷰
public class TempUserView {

    private static Scanner sc = new Scanner(System.in);
    private UserController userController = new UserController();

    public void startMenu() {

        int choice = 0;

        do {
            try {

                // 시작 화면 출력
                displayStartMenu();

                // 로그인 | 회원가입 | 정보 수정 입력
                choice = sc.nextInt();

                switch (choice) {

                    // 로그인
                    case 1:
                        if (UserController.isLogin) {
                            alreadyLogin();

                        } else {
                            String[] idpw = loginMenu();
                            userController.login(idpw[0], idpw[1]);
                        }
                        break;

                    // 회원가입
                    case 2:
                        if (UserController.isLogin) {
                            alreadyLogin();

                        } else {
                            UserDTO user = createUserMenu();
                            userController.signUp(user);

                        }
                        break;

                    // 회원 정보 수정
                    case 3:
                        if (!UserController.isLogin) {
                            System.out.println("로그인 상태가 아닙니다.");

                        } else {
                            int updateChoice = updateUserMenu();

                            if(updateChoice == 1) {
                                System.out.print("변경할 아이디 >> ");
                                userController.updateId(inputString());

                            }else if (updateChoice == 2) {
                                System.out.print("변경할 비밀번호 >> ");
                                userController.updatePw(inputString());

                            } else if (updateChoice == 3) {
                                System.out.print("변경할 닉네임 >> ");
                                String nickname = inputString();

                                System.out.print("변경할 나이 >> ");
                                int age = inputInt();

                                userController.updateInfo(nickname, age);
                            }

                        }
                        break;
                        
                    case 4:
                        String id = deleteUserMenu();
                        userController.deleteUser(id);

                    default:
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("잘못된 입력값입니다.");

            }

        } while (choice != -1);
    }



    private String[] loginMenu() {
        String[] idpw = new String[2];

        System.out.println("=================================");
        System.out.println("===========로그인 화면===========");
        System.out.print("아이디 입력 >> ");
        idpw[0] = inputString();

        System.out.print("비밀번호 입력 >> ");
        idpw[1] = inputString();

        return idpw;
    }

    private UserDTO createUserMenu() {
        UserDTO user = new UserDTO();

        System.out.println("=================================");
        System.out.println("==========회원가입 화면==========");

        System.out.print("사용할 아이디 >> ");
        user.setId(inputString());

        System.out.print("사용할 비밀번호 >> ");
        user.setPw(inputString());

        System.out.print("사용할 닉네임 >> ");
        user.setNickname(inputString());

        System.out.print("나이 >> ");
        user.setAge(inputInt());

        return user;
    }

    private int updateUserMenu() {
        System.out.println("=================================");
        System.out.println("==========회원 정보 수정==========");
        System.out.println("1. 아이디 변경");
        System.out.println("2. 비밀번호 변경");
        System.out.println("3. 닉네임, 나이 변경");
        System.out.print("입력 >> ");
        int choice = sc.nextInt();
        return choice;
    }

    private String deleteUserMenu() {
        System.out.println("=================================");
        System.out.println("============유저 삭제============");
        System.out.print("삭제할 유저 아이디 >> ");
        String id = inputString();

        return id;
    }


    // region METHODS-DISPLAY

    public void displayStartMenu() {
        System.out.println("=================================");
        System.out.println("=============시작화면=============");
        System.out.println("-1. 종료");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("3. 회원 정보 수정");
        System.out.print("입력 >> ");
    }

    public void alreadyLogin() {
        System.out.println("이미 로그인 상태입니다.");
        userController.selectOneById(UserController.myUserId);
    }

    // endregion
    // region METHODS-INPUT

    private String inputString() {
        return sc.next();
    }

    private int inputInt() {
        return sc.nextInt();
    }


    // endregion
}
