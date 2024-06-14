package com.ps.models;

public class LeaseContract extends Contract {
    private double expectedEndValue;
    private double leaseFee;
    
    public LeaseContract(
            String date,
            String customerName,
            String customerEmail,
            Vehicle vehicleSold)
    {
        super(date, customerName, customerEmail, vehicleSold);
        this.expectedEndValue = vehicleSold.getPrice() * .5; // 50% of original vehicle cost
        this.leaseFee = vehicleSold.getPrice() * 0.07; // 7% of original vehicle cost
    }
    
    public LeaseContract(
            int id,
            String date,
            String customerName,
            String customerEmail,
            Vehicle vehicleSold)
    {
        super(id, date, customerName, customerEmail, vehicleSold);
        this.expectedEndValue = vehicleSold.getPrice() * .5; // 50% of original vehicle cost
        this.leaseFee = vehicleSold.getPrice() * 0.07; // 7% of original vehicle cost
    }
    
    @Override
    public double getTotalPrice() {
        this.totalPrice = this.getExpectedEndValue() + this.leaseFee;
        return this.totalPrice;
    }
    
    @Override
    public double getMonthlyPayment() {
        this.monthlyPayment = this.getTotalPrice() * 0.04;
        return this.monthlyPayment;
    }
    
    public double getExpectedEndValue() {
        return expectedEndValue;
    }
    public void setExpectedEndValue(double expectedEndValue) {
        this.expectedEndValue = expectedEndValue;
    }
    
    public double getLeaseFee() {
        return leaseFee;
    }
    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }
    
    @Override
    public String toString() {
        return "LeaseContract{" +
                "expectedEndValue=" + this.expectedEndValue +
                ", leaseFee=" + this.leaseFee +
                ", totalPrice=" + this.totalPrice +
                ", monthlyPayment=" + this.monthlyPayment +
                '}';
    }
}

