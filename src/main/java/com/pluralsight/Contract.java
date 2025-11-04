package com.pluralsight;

import java.time.LocalDate;

public abstract class Contract {


    // variables
    private String contractDate;
    private String LocalDate = contractDate;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    private double totalPrice;
    private double monthlyPayments;

    // constructor
    public Contract(String contractDate, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    // getters and setters
    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
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

    //abstract getters
    public abstract double getTotalPrice();

    public abstract double getMonthlyPayments();

    public abstract String getContractType();
}
