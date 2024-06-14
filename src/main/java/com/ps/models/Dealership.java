package com.ps.models;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private int dealershipId;
    private String             name;
    private String             address;
    private String             phoneNum;
    private List<Vehicle> inventory; // inventory of all vehicles in Dealership
    
    public Dealership(String name, String address, String phoneNum) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.inventory = new ArrayList<>();
    }
    
    public Dealership(int dealershipId, String name, String address, String phoneNum) {
        this.dealershipId = dealershipId;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.inventory = new ArrayList<>();
    }
    
    public void addVehicle(Vehicle vehicle) {
        this.inventory.add(vehicle);
    }
    
    public void removeVehicle(Vehicle vehicle) {
        this.inventory.remove(vehicle);
    }
    
    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> priceRangeFilteredVehicles = new ArrayList<>();
        
        for (Vehicle vehicle : inventory) {
            double vehiclePrice = vehicle.getPrice();
            if (vehiclePrice <= max && vehiclePrice >= min) {
                priceRangeFilteredVehicles.add(vehicle);
            }
        }
        
        return priceRangeFilteredVehicles;
    }
    
    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> makeModelFilteredVehicles = new ArrayList<>();
        
        for (Vehicle vehicle : inventory) {
            String vehicleMake = vehicle.getMake();
            String vehicleModel = vehicle.getModel();
            
            if (vehicleMake.equalsIgnoreCase(make) && vehicleModel.equalsIgnoreCase(model)) {
                makeModelFilteredVehicles.add(vehicle);
            }
        }
        return makeModelFilteredVehicles;
    }
    
    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> yearRangeFilteredVehicles = new ArrayList<>();
        
        for (Vehicle vehicle : inventory) {
            int vehicleYear = vehicle.getYear();
            if (vehicleYear <= max && vehicleYear >= min) {
                yearRangeFilteredVehicles.add(vehicle);
            }
        }
        return yearRangeFilteredVehicles;
    }
    
    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> colorFilteredVehicles = new ArrayList<>();
        
        for (Vehicle vehicle : inventory) {
            String vehicleColor = vehicle.getColor();
            
            if (vehicleColor.equalsIgnoreCase(color)) {
                colorFilteredVehicles.add(vehicle);
            }
        }
        return colorFilteredVehicles;
    }
    
    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> mileageRangeFilteredVehicles = new ArrayList<>();
        
        for (Vehicle vehicle : inventory) {
            int vehicleMileage = vehicle.getOdometer();
            if (vehicleMileage <= max && vehicleMileage >= min) {
                mileageRangeFilteredVehicles.add(vehicle);
            }
        }
        return mileageRangeFilteredVehicles;
    }
    
    public List<Vehicle> getVehiclesByType(String inputVehicleType) {
        List<Vehicle> vehicleTypeFilteredVehicles = new ArrayList<>();
        
        for (Vehicle vehicle : inventory) {
            String vehicleType = vehicle.getVehicleType();
            
            if (vehicleType.equalsIgnoreCase(inputVehicleType)) {
                vehicleTypeFilteredVehicles.add(vehicle);
            }
        }
        return vehicleTypeFilteredVehicles;
    }
    
    public List<Vehicle> getAllVehicles() {
        return inventory;
    }
    
    public int getDealershipId() {
        return dealershipId;
    }
    
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
    
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    @Override
    public String toString() {
        return "Dealership{" +
                "dealershipId=" + dealershipId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
