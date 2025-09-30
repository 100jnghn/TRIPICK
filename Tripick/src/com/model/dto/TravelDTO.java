package com.model.dto;

public class TravelDTO {

    private int travelNo;
    private String district;
    private String title;
    private String desc;
    private String address;
    private String phone;
    private float sum;
    private int count;


    //getter & setter
    public int getTravelNo() {
        return travelNo;
    }

    public void setTravelNo(int travelNo) {
        this.travelNo = travelNo;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



    //toString
    @Override
    public String toString() {
        return "TravelDTO{" +
                "travelNo=" + travelNo +
                ", district='" + district + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", sum=" + sum +
                ", count=" + count +
                '}';
    }
}
