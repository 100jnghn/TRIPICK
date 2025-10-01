package com.view;

import com.Auth.LoginAccount;
import com.controller.UserController;
import com.model.dto.UserDTO;

import java.util.Scanner;

// 로그인 - 회원가입 - 유저 정보 수정 진행 뷰
public class StartView {

    private static Scanner sc = new Scanner(System.in);
    private UserController userController = new UserController();

    private LoginAccount loginAccount = new LoginAccount();

    public void startMenu() {

        int choice = 0;

        while (true) {
            try {

                // 시작 화면 출력
                displayStartMenu();

                // 로그인 | 회원가입 | 정보 수정 입력
                choice = sc.nextInt();

                switch (choice) {

                    // 종료
                    case -1:
                        displayEnding();
                        return;

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
                            displayMessage("로그인 상태가 아닙니다.");

                        } else {
                            int updateChoice = updateUserMenu();

                            if (updateChoice == 1) {
                                System.out.print("변경할 아이디 >> ");
                                userController.updateId(inputString());

                            } else if (updateChoice == 2) {
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
                        //관광지 검색, 리뷰 메뉴로 이동 메소드를 호출
                        if (UserController.isLogin) {
                            TempReviewView view = new TempReviewView();
                            view.middleMenu();

                        } else {
                            displayMessage("로그인 상태가 아닙니다.");
                        }
                        break;

                    // 유저 삭제 기능
                    // 요구사항엔 없는데 테스트를 위해 만들었음
                    case 5:
                        String id = deleteUserMenu();
                        userController.deleteUser(id);

                    default:
                        displayMessage("잘못된 입력값입니다.");
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private String[] loginMenu() {
        String[] idpw = new String[2];

        System.out.println("\n╔═════════════════════════════════╗");
        System.out.println("║        🔐 로그인 화면 🔐        ║");
        System.out.println("╚═════════════════════════════════╝\n");

        System.out.println("─ 계정 정보를 입력해 주세요 ─────────────────");

        System.out.print("  아이디 (ID)             : ");
        idpw[0] = inputString();

        System.out.print("  비밀번호 (PW)           : ");
        idpw[1] = inputString();

        System.out.println("──────────────────────────────────────────\n");

        return idpw;
    }

    private UserDTO createUserMenu() {
        UserDTO user = new UserDTO();

        System.out.println("\n╔═════════════════════════════════╗");
        System.out.println("║        ✨ 회원 가입 화면 ✨     ║");
        System.out.println("╚═════════════════════════════════╝\n");

        System.out.println("─ 입력 정보를 정확히 기입해주세요 ─────────────────");

        System.out.print("  ID (사용자 아이디)      : ");
        user.setId(inputString());

        System.out.print("  PW (비밀번호)           : ");
        user.setPw(inputString());

        System.out.print("  닉네임 (표시 이름)      : ");
        user.setNickname(inputString());

        System.out.print("  나이 (숫자만 입력)      : ");
        user.setAge(inputInt());

        System.out.println("──────────────────────────────────────────\n");

        return user;
    }

    private int updateUserMenu() {
        System.out.println("\n╔═════════════════════════════════╗");
        System.out.println("║       🛠️ 회원 정보 수정 🛠️     ║");
        System.out.println("╚═════════════════════════════════╝\n");

        // 명확하게 옵션을 구분합니다.
        System.out.println("   [1] 아이디 변경 (ID)");
        System.out.println("   [2] 비밀번호 변경 (PW)");
        System.out.println("   [3] 닉네임 및 나이 변경");
        System.out.println("   [0] 이전 화면으로 돌아가기"); // 선택의 여지를 줍니다.

        System.out.println("───────────────────────────────────");
        System.out.print("  수행할 작업 번호를 입력하세요 >> ");

        int choice = sc.nextInt();
        return choice;
    }

    private String deleteUserMenu() {
        System.out.println("\n╔═════════════════════════════════╗");
        System.out.println("║      🗑️ 사용자 계정 삭제 🗑️     ║");
        System.out.println("╚═════════════════════════════════╝\n");

        System.out.println("─ 주의: 삭제된 계정은 복구할 수 없습니다 ─────────");

        System.out.print("  삭제할 유저 ID          : ");
        String id = inputString();

        System.out.println("──────────────────────────────────────────\n");

        return id;
    }


    // region METHODS-DISPLAY

    public void displayStartMenu() {
        System.out.println("\n╔═════════════════════════════════╗");
        System.out.println("║          ✨ Tripick ✨         ║"); // 프로그램 이름으로 변경하세요!
        System.out.println("╠═════════════════════════════════╣");
        System.out.println("║         🚀 시작화면 🚀         ║");
        System.out.println("╚═════════════════════════════════╝\n");

        System.out.println("   [1] 로그인 (Login)");
        System.out.println("   [2] 회원가입 (Sign Up)");
        System.out.println("   [3] 회원 정보 수정 (Edit Info)");
        System.out.println("   [4] 메인 화면으로 이동 ");
        System.out.println("───────────────────────────────────");
        System.out.println("   [-1] 프로그램 종료");

        System.out.println("───────────────────────────────────");
        System.out.print("  실행할 메뉴 번호를 입력하세요 >> ");
    }

    public void alreadyLogin() {
        System.out.println("\n╔═════════════════════════════════╗");
        System.out.println("║       ✅ 현재 상태 알림 ✅      ║");
        System.out.println("╠═════════════════════════════════╣");
        System.out.println("║      이미 로그인 상태입니다.     ║");
        System.out.println("║ 계속해서 서비스를 이용해 주세요. ║");
        System.out.println("╚═════════════════════════════════╝\n");
        userController.selectOneById(UserController.myUserId);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    private void displayEnding() {
        System.out.println("\n╔═════════════════════════════════╗");
        System.out.println("║       👋 프로그램 종료 👋      ║");
        System.out.println("╠═════════════════════════════════╣");
        System.out.println("║   저희 프로그램을 이용해 주셔서  ║");
        System.out.println("║   진심으로 감사합니다. (FIN)     ║");
        System.out.println("╚═════════════════════════════════╝\n");
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
