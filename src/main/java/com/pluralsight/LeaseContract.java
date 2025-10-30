package com.pluralsight;


public class LeaseContract extends Contract{

    // variables
    private double expectedEndingValue;
    private double leaseFee;
    private final double interestRate = 0.04; // annual 4%
    private final int leaseTermMonths = 36;

    // constuctor
    public LeaseContract(String contractDate, String customerName, String customerEmail, Vehicle vehicleSold,
                         double expectedEndingValue, double leaseFee) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        double price = vehicleSold.getPrice();
        this.expectedEndingValue = price * 0.50; // 50% of original price
        this.leaseFee = price * 0.07; // 7% of original price
    }

    // total price calculator
    @Override
    public double getTotalPrice() {
        return leaseFee + expectedEndingValue;
    }

    // monthly payment calculator
    @Override
    public double getMonthlyPayments(){
        double totalPrice = getTotalPrice();
        double monthlyRate = interestRate / 12;
        return (totalPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, leaseTermMonths));
    }

    // getters and setters

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getLeaseTermMonths() {
        return leaseTermMonths;
    }
}
