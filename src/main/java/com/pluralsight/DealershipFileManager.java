package com.pluralsight;

import java.io.*;

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
                String vin = data[0].trim();
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
    public void saveDealership(Dealership dealership) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();

            for (Vehicle v : dealership.getAllVehicles()) {
                writer.write(v.getVin() + "|" + v.getYear() + "|" + v.getMake() + "|" + v.getModel() + "|" +
                        v.getVehicleType() + "|" + v.getColor() + "|" + v.getOdometer() + "|" + v.getPrice());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving dealership file: " + e.getMessage());
        }
    }
}
