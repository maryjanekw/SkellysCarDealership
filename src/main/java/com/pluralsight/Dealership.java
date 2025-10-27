package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    // Getters and Setters
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

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vehicle> getInventory() {
        return  new ArrayList<>(inventory);
    }

    // Search methods
    public List<Vehicle> getVehiclesByPrice(double min, double max){
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                matches.add(v);
            }
        }
        return matches;
    }

    public List<Vehicle> getVehicleByMakeModel(String make, String model){
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getMake().equalsIgnoreCase(make)
                    && v.getModel().equalsIgnoreCase(model)) {
                matches.add(v);
            }
        }
        return matches;
    }

    public List<Vehicle> getVehicleByYear (int min, int max){
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getYear() >= min && v.getYear() <= max) {
                matches.add(v);
            }
        }
        return matches;
    }

    public List<Vehicle> getVehicleByColor(String color){
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                matches.add(v);
            }
        }
        return matches;
    }

    public List<Vehicle> getVehicleByMileage(int min, int max){
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getOdometer() >= min && v.getOdometer() <= max) {
                matches.add(v);
            }
        }
        return matches;
    }

    public List<Vehicle> getVehicleByType(String vehicleType){
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getVehicleType().equalsIgnoreCase(vehicleType)) {
                matches.add(v);
            }
        }
        return matches;
    }

    // Functional methods
    public List<Vehicle> getAllVehicles(){
        return new ArrayList<>(inventory);
    }

    public void addVehicle(Vehicle vehicle){
        if (vehicle != null){
            inventory.add(vehicle);
        }
    }

    public void removeVehicle(Vehicle vehicle){
        inventory.remove(vehicle);
    }

    @Override
    public String toString() {
        return name + " | " + address + " | " + phone + " | " + inventory.size() + " vehicles";
    }


}
