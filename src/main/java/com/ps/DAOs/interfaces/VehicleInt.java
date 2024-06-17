package com.ps.DAOs.interfaces;

import com.ps.models.Vehicle;
import java.util.List;

public interface VehicleInt {
    List<Vehicle> getAllVehicles();
    Vehicle getOneVehicle(int vin);
    List<Vehicle> getVehiclesByPrice(double min, double max);
    List<Vehicle> getVehiclesByMakeModel(String make, String model);
    List<Vehicle> getVehiclesByYear(int min, int max);
    List<Vehicle> getVehiclesByColor(String color);
    List<Vehicle> getVehiclesByMileage(int min, int max);
    List<Vehicle> getVehiclesByType(String vehicleType);
    void addVehicle(Vehicle vehicle);
    void removeVehicle(int vin);
}
