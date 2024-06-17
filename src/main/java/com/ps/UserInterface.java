package com.ps;

import com.ps.DAOs.VehicleDAO;
import com.ps.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static VehicleDAO vehicleDAO;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void init(String[] args) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/dealership_db");
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(args[0]);
        basicDataSource.setPassword(args[1]);
        
        vehicleDAO = new VehicleDAO(basicDataSource);
    }
    
    public static void display(String[] args) {
        init(args);
    
        List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
//        List<Vehicle> vehicles = vehicleDAO.getVehiclesByPrice(995, 996);
//        List<Vehicle> vehicles = vehicleDAO.getVehiclesByMakeModel("Ford", "Explorer");
//        List<Vehicle> vehicles = vehicleDAO.getVehiclesByColor("silver");
//        List<Vehicle> vehicles = vehicleDAO.getVehiclesByMileage(1, 1);
//        List<Vehicle> vehicles = vehicleDAO.getVehiclesByType("suv");
//        Vehicle vehicle = new Vehicle(93335, 2024, "Bugatti", "W16 Mistral", "Sports-car", "Magenta", 424_535, 5_032_943.65);
//        vehicleDAO.addVehicle(vehicle);
//        vehicleDAO.removeVehicle();
//        System.out.println(vehicles);
        
        displayVehicles(vehicles);
    }
    
    public static void displayMainMenu() {
        int command;
        do {
            System.out.println("1) Find vehicles within a price range");
            System.out.println("2) Find vehicles by make/model");
            System.out.println("3) Find vehicles by year range");
            System.out.println("4) Find vehicles by color");
            System.out.println("5) Find vehicles by mileage range");
            System.out.println("6) Find vehicles by type (car, truck, SUV, van)");
            System.out.println("7) List ALL vehicles");
            System.out.println("8) Add a vehicle");
            System.out.println("9) Remove a vehicle");
            System.out.println("10) Sales Contract");
            System.out.println("11) Lease Contract");
            System.out.println("99) Quit");
            
            System.out.println("\nEnter selection here:");
            command = scanner.nextInt();
            
            
            switch (command) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
//                case 10:
//                    processSalesContract();
//                    break;
//                case 11:
//                    processLeaseContract();
//                    break;
                case 99:
                    System.out.println("\nExiting...");
                    break;
                default:
                    System.out.println("\nERROR: Must select options 1 - 9 or 99!\n\n");
            }
        } while (command != 99);
        scanner.close();
    }
    
    private static void displayVehicles(List<Vehicle> vehicles) {
        String saleStatus;
        
        System.out.println("\n");
        for (Vehicle vehicle : vehicles) {
//            String odometerStr = "" + vehicle.getOdometer();
//            String priceStr = "" + vehicle.getPrice();
            
            if (vehicle.getSold().equalsIgnoreCase("NO")) {
                saleStatus = "NOT SOLD";
            } else {
                saleStatus = "SOLD";
            }
    
//            String odometerStrCopy = "";
//
//            if (odometerStr.length() % 2 == 0) {
//                for (int i = 1; i <= odometerStr.length(); i++) {
//                    odometerStrCopy += odometerStr.charAt(i-1);
//                    if (i != odometerStr.length() && i % 3 == 0) {
//                        odometerStrCopy += ",";
//                    }
//                }
//            } else {
//                for (int i = odometerStr.length(), index = 0; i > 0; i--, index++) {
//                    odometerStrCopy += odometerStr.charAt(index);
//                    if (i % 4 == 0) {
//                        odometerStrCopy += ",";
//                    }
//                }
//            }
            
            
            System.out.printf("%-6d | %-5d | %-20s | %-35s | %-22s | %-10s | %-7d | $%-8.2f | %-8s\n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice(),
                    saleStatus
            );
        }
        System.out.println("\n");
    }
    
    private static void displayOneVehicle(Vehicle vehicle) {
        String saleStatus;
        
        System.out.println("\n");
    
        if (vehicle.getSold().equalsIgnoreCase("NO")) {
            saleStatus = "NOT SOLD";
        } else {
            saleStatus = "SOLD";
        }
    
        System.out.printf("%-6d | %-5d | %-20s | %-35s | %-22s | %-10s | %-7d | $%-8.2f | %-8s\n",
                vehicle.getVin(),
                vehicle.getYear(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getVehicleType(),
                vehicle.getColor(),
                vehicle.getOdometer(),
                vehicle.getPrice(),
                saleStatus
        );
        
        System.out.println("\n");
    }
    
    public static void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
        String        pageTitle           = "Displaying all Vehicles";

        if (!vehicles.isEmpty()) {
            System.out.printf("\n%45s\n", pageTitle);
            displayVehicles(vehicles);
        } else {
            System.out.println("\nThis dealership has no available vehicles.\n\n");
        }
    }

    public static void processGetByPriceRequest() {
        float  min;
        float  max;
        String pageTitle = "Displaying all Vehicles By Price";

        System.out.println("\nEnter a minimum price:");
        min = scanner.nextFloat();

        System.out.println("\nEnter a maximum price:");
        max = scanner.nextFloat();

        List<Vehicle> vehicles = vehicleDAO.getVehiclesByPrice(min, max);
        if (!vehicles.isEmpty()) {
            System.out.printf("\n%45s\n", pageTitle);
            displayVehicles(vehicles);
        } else {
            System.out.println("\nThere are no vehicles within this price range.\n\n");
        }
    }

    public static void processGetByMakeModelRequest() {
        String make;
        String model;
        String pageTitle = "Displaying all Vehicles By Make and Model";

        // Consumes extra carriage
        scanner.nextLine();

        System.out.println("\nEnter vehicle make:");
        make = scanner.nextLine();

        System.out.println("\nEnter vehicle model:");
        model = scanner.nextLine();

        List<Vehicle> vehicles = vehicleDAO.getVehiclesByMakeModel(make, model);
        if (!vehicles.isEmpty()) {
            System.out.printf("\n%48s\n", pageTitle);
            displayVehicles(vehicles);
        } else {
            System.out.println("\nEither there are no vehicles with this make and model or your input was spelled incorrectly.\n\n");
        }
    }

    public static void processGetByYearRequest() {
        int    min;
        int    max;
        String pageTitle = "Displaying all Vehicles By Year";

        System.out.println("\nFrom (year):");
        min = scanner.nextInt();

        System.out.println("\nTo (year):");
        max = scanner.nextInt();

        List<Vehicle> vehicles = vehicleDAO.getVehiclesByYear(min, max);
        if (!vehicles.isEmpty()) {
            System.out.printf("\n%45s\n", pageTitle);
            displayVehicles(vehicles);
        } else {
            System.out.println("\nThere are no vehicles within this year range.\n\n");
        }
    }

    public static void processGetByColorRequest() {
        String color;
        String pageTitle = "Displaying all Vehicles By Color";

        // Consumes extra carriage
        scanner.nextLine();

        System.out.println("\nEnter vehicle color:");
        color = scanner.nextLine();

        List<Vehicle> vehicles = vehicleDAO.getVehiclesByColor(color);
        if (!vehicles.isEmpty()) {
            System.out.printf("\n%45s\n", pageTitle);
            displayVehicles(vehicles);
        } else {
            System.out.println("\nEither there are no vehicles with this color or your input was spelled incorrectly.\n\n");
        }
    }

    public static void processGetByMileageRequest() {
        int    min;
        int    max;
        String pageTitle = "Displaying all Vehicles By Mileage";

        System.out.println("\nEnter minimum mileage:");
        min = scanner.nextInt();

        System.out.println("\nEnter maximum mileage:");
        max = scanner.nextInt();

        List<Vehicle> vehicles = vehicleDAO.getVehiclesByMileage(min, max);
        if (!vehicles.isEmpty()) {
            System.out.printf("\n%45s\n", pageTitle);
            displayVehicles(vehicles);
        } else {
            System.out.println("\nThere are no vehicles within this mileage range.\n\n");
        }
    }

    public static void processGetByVehicleTypeRequest() {
        String vehicleType;
        String pageTitle = "Displaying all Vehicles By Vehicle Type";

        // Consumes extra carriage
        scanner.nextLine();

        System.out.println("\nEnter vehicle type:");
        vehicleType = scanner.nextLine();

        List<Vehicle> vehicles = vehicleDAO.getVehiclesByType(vehicleType);
        if (!vehicles.isEmpty()) {
            System.out.printf("\n%48s\n", pageTitle);
            displayVehicles(vehicles);
        } else {
            System.out.println("\nEither there are no vehicles with this vehicle type or your input was spelled incorrectly.\n\n");
        }
    }

    public static void processAddVehicleRequest() {
        int    vin;
        int    year;
        String make;
        String model;
        String vehicleType;
        String color;
        int    odometer;
        float  price;

        System.out.println("\nYou selected the Add Vehicle option.\n");

        System.out.println("What is the vehicle's vin#?");
        vin = scanner.nextInt();

        System.out.println("What was the release year of the vehicle?");
        year = scanner.nextInt();

        // Consumes extra carriage
        scanner.nextLine();

        System.out.println("What is the make of this vehicle? (e.g. Honda)");
        make = scanner.nextLine();

        System.out.println("What is the vehicle model? (e.g. Civic)");
        model = scanner.nextLine();

        System.out.println("What is the vehicle type? (e.g. SUV)");
        vehicleType = scanner.nextLine();

        System.out.println("What is the color of the vehicle?");
        color = scanner.nextLine();

        System.out.println("What is the vehicle's mileage?");
        odometer = scanner.nextInt();

        System.out.println("What is the price of the vehicle?");
        price = scanner.nextFloat();

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        
        vehicleDAO.addVehicle(vehicle);
        System.out.printf("\nYou added: %d | %d | %s | %s | %s | %s | %d | %.2f\n\n\n",
                vehicle.getVin(),
                vehicle.getYear(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getVehicleType(),
                vehicle.getColor(),
                vehicle.getOdometer(),
                vehicle.getPrice()
        );
    }

    public static void processRemoveVehicleRequest() {
        int vin;

        System.out.println("\nYou selected the Remove a Vehicle option.\n");

        // Ask for vehicle details
        System.out.println("What is the VIN of the vehicle you would like to remove?");
        vin = scanner.nextInt();
        
        Vehicle vehicle = vehicleDAO.getOneVehicle(vin);
        
        if (vehicle != null) {
            System.out.printf("\nYou removed: %d | %d | %s | %s | %s | %s | %d | %.2f | %s\n\n\n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice(),
                    vehicle.getSold()
            );
            vehicleDAO.removeVehicle(vin);
        } else {
            System.out.println("\n\t~Vehicle not found~\n\n");
        }
    }
//
//    public static void processSalesContract() {
//        LocalDate date = LocalDate.now();
//        String    customerName;
//        String    customerEmail;
//
//        int     vinInput;
//        Vehicle vehicleSold = null; // place-holder
//
//        String  financeOptionStr;
//        boolean financeOption = false; // place-holder
//
//        DateTimeFormatter formatter     = DateTimeFormatter.ofPattern("yyyyMMdd");
//        String            formattedDate = date.format(formatter);
//
//        // Consumes extra carriage
//        scanner.nextLine();
//
//        System.out.println("\n\nYou selected the Sales Contract option!\n");
//
//        System.out.println("Please enter your first and last name:");
//        customerName = scanner.nextLine();
//
//        System.out.println("Please enter your email address:");
//        customerEmail = scanner.nextLine();
//
//        System.out.println("Please enter the vin# of the vehicle you would like to purchase:");
//        vinInput = scanner.nextInt();
//
//        int vehicleVin;
//        for (Vehicle vehicle : this.dealership.getAllVehicles()) {
//            vehicleVin = vehicle.getVin(); // vin# of each vehicle in the dealership
//            if (vehicleVin == vinInput) {
//                vehicleSold = vehicle;
//                System.out.printf("You selected: %d | %d | %s | %s | %s | %s | %d | %.2f\n\n",
//                        vehicleSold.getVin(),
//                        vehicleSold.getYear(),
//                        vehicleSold.getMake(),
//                        vehicleSold.getModel(),
//                        vehicleSold.getVehicleType(),
//                        vehicleSold.getColor(),
//                        vehicleSold.getOdometer(),
//                        vehicleSold.getPrice()
//                );
//                break;
//            }
//        }
//
//        // Consumes extra carriage
//        scanner.nextLine();
//
//        System.out.println("Would you like to finance? (YES/NO)");
//        financeOptionStr = scanner.nextLine();
//
//        if (financeOptionStr.equalsIgnoreCase("YES")) {
//            financeOption = true;
//        } else if (financeOptionStr.equalsIgnoreCase("NO")) {
//            financeOption = false;
//        }
//
//        SalesContract salesContract = new SalesContract(formattedDate, customerName, customerEmail, vehicleSold, financeOption);
//        ContractFileManager.saveContract(salesContract);
//
//        this.dealership.removeVehicle(vehicleSold);
//        DealershipFileManager.saveDealership(this.dealership);
//    }
//
//    public static void processLeaseContract() {
//        LocalDate date = LocalDate.now();
//        String    customerName;
//        String    customerEmail;
//
//        int     vinInput;
//        Vehicle vehicleSold = null; // place-holder
//
//        DateTimeFormatter formatter     = DateTimeFormatter.ofPattern("yyyyMMdd");
//        String            formattedDate = date.format(formatter);
//
//        // Consumes extra carriage
//        scanner.nextLine();
//
//        System.out.println("\n\nYou selected the Lease Contract option!\n");
//
//        System.out.println("Please enter your first and last name:");
//        customerName = scanner.nextLine();
//
//        System.out.println("Please enter your email address:");
//        customerEmail = scanner.nextLine();
//
//        System.out.println("Please enter the vin# of the vehicle you would like to purchase:");
//        vinInput = scanner.nextInt();
//
//        int vehicleVin;
//        for (Vehicle vehicle : this.dealership.getAllVehicles()) {
//            vehicleVin = vehicle.getVin(); // vin# of each vehicle in the dealership
//            if (vehicleVin == vinInput) {
//                vehicleSold = vehicle;
//                System.out.printf("You selected: %d | %d | %s | %s | %s | %s | %d | %.2f\n\n",
//                        vehicleSold.getVin(),
//                        vehicleSold.getYear(),
//                        vehicleSold.getMake(),
//                        vehicleSold.getModel(),
//                        vehicleSold.getVehicleType(),
//                        vehicleSold.getColor(),
//                        vehicleSold.getOdometer(),
//                        vehicleSold.getPrice()
//                );
//                break;
//            }
//        }
//
//        LeaseContract leaseContract = new LeaseContract(formattedDate, customerName, customerEmail, vehicleSold);
//        ContractFileManager.saveContract(leaseContract);
//
//        this.dealership.removeVehicle(vehicleSold);
//        DealershipFileManager.saveDealership(this.dealership);
//    }
//
}
