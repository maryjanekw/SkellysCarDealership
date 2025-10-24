package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import  java.io.IOException;

public class DealershipFileManager {

    private static final String fileName = "inventory.csv";

    public Dealership getDealearship(){
        Dealership dealership = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String dealershipInfo = reader.readLine();
            if (dealershipInfo != null){
                String[] part = dealershipInfo.split("\\|");
                String name = part[0].trim();
                String address = part[1].trim();
                String phone = part[2].trim();
                dealership = new Dealership(name, address, phone);
            }
            String line;
            while((line = reader.readLine()) != null){
                String[] data = line.split("\\|");
                int vin = Integer.parseInt(data[0]);
                int year = Integer.parseInt(data[1]);
                String make = data[2].trim();
                String model = data[3].trim();
                String vehicleType = data[4].trim();
                String color = data[5].trim();
                int odometer = Integer.parseInt(data[6]);
                double price = Double.parseDouble(data[7]);
                Vehicle vehicle = new Vehicle(vin, year, make, model,vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);
            }
        }catch (IOException ex){
            System.out.println("Error reading dealership file: " + ex.getMessage());
        }
        return dealership;
    }
    public void saveDealership(Dealership dealership){

    }
}
