package com.pluralsight;

public class SalesContract extends Contract{

    // variables
    private double salesTaxAmount;
    private double recordingFee = 100.00;
    private double processingFee;
    private boolean financed;

    //constuctor
    public SalesContract(String contractDate, String customerName, String customerEmail, Vehicle vehicleSold,
                         boolean isFinanced) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        this.financed = financed;
        double price = vehicleSold.getPrice();
        this.salesTaxAmount = price * 0.05; // sales tax 5%
        this.processingFee = price < 10000 ? 295.0 : 495.0; //  295 if < 10k > if 495
    }

    // total payment calculator
    @Override
    public double getTotalPrice(){
        return getVehicleSold().getPrice() + salesTaxAmount + recordingFee + processingFee;
    }

    // monthly payment calculator
    @Override
    public double getMonthlyPayments() {
        if (!financed) { // if financed = false
            return 0.0;
        }
        double totalPrice = getTotalPrice();
        double interestRate;
        int months;

        if (totalPrice >= 10000) { // if financed = true and >= 10k interest = 4.25% & 48 months
            interestRate = 0.0425;
            months = 48;
        } else { // else if true < 10k interest 5.25% and 24 months
            interestRate = 0.525;
            months = 24;
        }
        double monthlyRate = interestRate / 12;
        return (totalPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, months));
    }

    // getters and setters
    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanced() {
        return financed;
    }

    public void setFinanced(boolean financed) {
        financed = financed;
    }

    // financed true/false = yes/no
    public String getFinancedAsText(){
        return financed ? "Yes" : "No";
    }
}
