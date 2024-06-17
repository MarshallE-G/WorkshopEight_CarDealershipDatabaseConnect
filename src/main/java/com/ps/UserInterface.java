package com.ps;

import com.ps.DAOs.VehicleDAO;
import com.ps.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class UserInterface {
    // Change UserInterface in order to save to the Database instead of writing to the file.
    private static VehicleDAO vehicleDAO;
    
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
    
//        List<Vehicle> vehicles = vehicleDAO.getVehiclesByPrice(995, 996);
//        List<Vehicle> vehicles = vehicleDAO.getVehiclesByMakeModel("Ford", "Explorer");
//        List<Vehicle> vehicles = vehicleDAO.getVehiclesByColor("silver");
//        List<Vehicle> vehicles = vehicleDAO.getVehiclesByMileage(1, 1);
//        List<Vehicle> vehicles = vehicleDAO.getVehiclesByType("suv");
        Vehicle vehicle = new Vehicle(93335, 2024, "Bugatti", "W16 Mistral", "Sports-car", "Magenta", 424_535, 5_032_943.65);
        vehicleDAO.addVehicle(vehicle);
//        vehicleDAO.removeVehicle();
//        System.out.println(vehicles);
    }
    
    
}
