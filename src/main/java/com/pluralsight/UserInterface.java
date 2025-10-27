package com.pluralsight;

import java.util.Scanner;
import java.util.List;

public class UserInterface {

    private Dealership dealership;

    public UserInterface() {
    }

    //  Menu
    private void displayMenu(){
        System.out.println("========================================");
        System.out.println("       DEALERSHIP MANAGEMENT MENU       ");
        System.out.println("========================================");
        System.out.println("1 - Find vehicles within a price range");
        System.out.println("2 - Find vehicles by make / model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by type");
        System.out.println("7 - List ALL vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("99 - Quit");
        System.out.println("========================================");
    }

    // Display
    public void display(){
        init();
        Scanner read = new Scanner(System.in);
        boolean running = true;

        while (running){
            displayMenu();
            System.out.println("Enter your choice: ");
            String choice = read.nextLine();

            switch(choice){
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processGetAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "99":
                    System.out.println("Exiting program... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            if (running) {
                System.out.println("\nPress ENTER to return to menu...");
                read.nextLine();
            }
        }
        read.close();
    }

    // display init
    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealearship();
        System.out.println("Dealership loaded successfully!\n");
    }

    // display Vehicles
    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.println("\n------ VEHICLE INVENTORY ------");
        for (Vehicle v : vehicles) {
            System.out.println(
                    v.getYear() + " " + v.getMake() + " " + v.getModel() +
                            " | " + v.getColor() +
                            " | " + v.getOdometer() + " miles" +
                            " | $" + v.getPrice() +
                            " | Type: " + v.getVehicleType());
        }
        System.out.println("------------------------------\n");
    }

    // Process all Vehicle Requests
    public void processGetAllVehiclesRequest(){
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    public void processGetByPriceRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum price: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter maximum price: ");
        double max = Double.parseDouble(scanner.nextLine());

        List<Vehicle> results = dealership.getVehiclesByPrice(min, max);
        displayVehicles(results);
    }

    public void processGetByMakeModelRequest() {}

    public void processGetByYearRequest() {}

    public void processGetByColorRequest() {}

    public void processGetByMileageRequest() {}

    public void processGetByVehicleTypeRequest() {}

    public void processAddVehicleRequest() {
        Scanner read = new Scanner(System.in);

        System.out.println("Enter VIN: ");
        String vin = read.nextLine();
        System.out.println("Enter year: ");
        int year = Integer.parseInt(read.nextLine());
        System.out.println("Enter make: ");
        String make = read.nextLine();
        System.out.println("Enter model: ");
        String model = read.nextLine();
        System.out.println("Enter type: ");
        String type = read.nextLine();
        System.out.println("Enter color: ");
        String color = read.nextLine();
        System.out.println("Enter odometer reading: ");
        int odometer = Integer.parseInt(read.nextLine());
        System.out.println("Enter price: ");
        double price = Double.parseDouble(read.nextLine());

        Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(newVehicle);

        // Save changes
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealershipFileManager.saveDealership(dealership);
        System.out.println("Vehicle added successfully!");
    }

    public void processRemoveVehicleRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter VIN of vehicle to remove: ");
        String vin = scanner.nextLine();

        Vehicle toRemove = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (Boolean.parseBoolean(v.getVin())) {
                toRemove = v;
                break;
            }
        }

        if (toRemove != null) {
            dealership.removeVehicle(toRemove);
            DealershipFileManager dealershipFileManager = new DealershipFileManager();
            dealershipFileManager.saveDealership(dealership);
            System.out.println("Vehicle removed successfully!");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

}
