package com.ps.models;

public class Dealership {
    private int    dealershipId;
    private String name;
    private String address;
    private String phoneNum;
    
    public Dealership(String name, String address, String phoneNum) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
    }
    
    public Dealership(int dealershipId, String name, String address, String phoneNum) {
        this.dealershipId = dealershipId;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
    }
    
    public int getDealershipId() {
        return dealershipId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhoneNum() {
        return phoneNum;
    }
    
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    @Override
    public String toString() {
        return "Dealership{" +
                "dealershipId=" + dealershipId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
