package com.model.dto;

import java.util.Date;

public class UserDTO {

    // region FIELDS

    private int userNo;
    private String id;
    private String pw;
    private String nickname;
    private int age;
    private Date createdAt;
    private Date updatedAt;

    // endregion
    // region GETTER SETTER

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    // endregion
    // region METHODS

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }


    // endregion
}
