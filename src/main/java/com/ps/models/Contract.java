package com.ps.models;

public abstract class Contract {
    private int id;
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    protected double totalPrice;
    protected double monthlyPayment;
    
    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }
    
    public Contract(int id, String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.id = id;
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }
    
    public abstract double getTotalPrice();
    
    public abstract double getMonthlyPayment();
    
    public int getId() {
        return id;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerEmail() {
        return customerEmail;
    }
    
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    
    public Vehicle getVehicleSold() {
        return vehicleSold;
    }
    
    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }
    
    @Override
    public String toString() {
        return "Contract{" +
                "id=" + this.id +
                ", date='" + this.date + '\'' +
                ", customerName='" + this.customerName + '\'' +
                ", customerEmail='" + this.customerEmail + '\'' +
                ", vehicleSold=" + this.vehicleSold +
                ", totalPrice=" + this.totalPrice +
                ", monthlyPayment=" + this.monthlyPayment +
                '}';
    }
}

