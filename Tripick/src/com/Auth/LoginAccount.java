package com.Auth;

public class LoginAccount {

    // region FIELDS
    private static final LoginAccount instance = new LoginAccount();

    private boolean isLogin = false;
    private String userId;
    private int userNo;

    // endregion
    // region GETTER SETTER

    public static LoginAccount getInstance() {
        return instance;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    // endregion
}
