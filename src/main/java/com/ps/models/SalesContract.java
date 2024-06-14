package com.ps.models;

public class SalesContract extends Contract {
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean financeOption;
    
    public SalesContract(
            String date,
            String customerName,
            String customerEmail,
            Vehicle vehicleSold,
            boolean financeOption) {
        super(date, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = 0.05 * vehicleSold.getPrice(); // 5% of the vehicle's price amount
        this.recordingFee = 100;
        this.processingFee = (vehicleSold.getPrice() < 10_000 ? 295 : 495);
        this.financeOption = financeOption;
    }
    
    public SalesContract(
            int id,
            String date,
            String customerName,
            String customerEmail,
            Vehicle vehicleSold,
            boolean financeOption) {
        super(id, date, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = 0.05 * vehicleSold.getPrice(); // 5% of the vehicle's price amount
        this.recordingFee = 100;
        this.processingFee = (vehicleSold.getPrice() < 10_000 ? 295 : 495);
        this.financeOption = financeOption;
    }
    
    @Override
    public double getTotalPrice() {
        this.totalPrice = getVehicleSold().getPrice() + this.salesTaxAmount + this.recordingFee + this.processingFee;
        return this.totalPrice;
    }
    
    @Override
    public double getMonthlyPayment() {
        double totalPriceOfVehicleSold = this.getTotalPrice();
        
        if (this.financeOption && (totalPriceOfVehicleSold > 9_999)) { // $10,000 or more
            this.monthlyPayment = totalPriceOfVehicleSold * 0.0425; // 4.25%
            
        } else if (this.financeOption && (totalPriceOfVehicleSold < 10_000)) { // If LESS than $10,000
            this.monthlyPayment = totalPriceOfVehicleSold * 0.0525; // 5.25%
            
        } else {
            this.monthlyPayment = 0;
        }
        return this.monthlyPayment;
    }
    
    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }
    
    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }
    
    public double getRecordingFee() {
        return recordingFee;
    }
    
    public void setRecordingFee(int recordingFee) {
        this.recordingFee = recordingFee;
    }
    
    public double getProcessingFee() {
        return processingFee;
    }
    
    public void setProcessingFee(int processingFee) {
        this.processingFee = processingFee;
    }
    
    public boolean getFinanceOption() {
        return financeOption;
    }
    
    public void setFinanceOption(boolean financeOption) {
        this.financeOption = financeOption;
    }
    
    @Override
    public String toString() {
        return "SalesContract{" +
                "salesTaxAmount=" + this.salesTaxAmount +
                ", recordingFee=" + this.recordingFee +
                ", processingFee=" + this.processingFee +
                ", financeOption=" + this.financeOption +
                ", totalPrice=" + this.totalPrice +
                ", monthlyPayment=" + this.monthlyPayment +
                '}';
    }
}

