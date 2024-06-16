package com.ps.DAOs;

import com.ps.DAOs.interfaces.VehicleInt;
import com.ps.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO implements VehicleInt {
    private BasicDataSource basicDataSource;
    
    public VehicleDAO(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }
    
    @Override
    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> vehicles = new ArrayList<>();
        
        try (
                Connection connection = basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE vehicle_price >= ? AND vehicle_price <= ?"
                );
        ) {
            preparedStatement.setDouble(1, min);
            preparedStatement.setDouble(2, max);
            
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    Vehicle vehicle = generateVehicleFromRS(resultSet);
                    
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        } finally {
            return vehicles;
        }
    }
    
    @Override
    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return null;
    }
    
    @Override
    public List<Vehicle> getVehiclesByYear(int min, int max) {
        return null;
    }
    
    @Override
    public List<Vehicle> getVehiclesByColor(String color) {
        return null;
    }
    
    @Override
    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        return null;
    }
    
    @Override
    public List<Vehicle> getVehiclesByType(String inputVehicleType) {
        return null;
    }
    
    public Vehicle generateVehicleFromRS(ResultSet resultSet) throws SQLException {
        int vin = resultSet.getInt("VIN");
        int year = resultSet.getInt("year");
        String make = resultSet.getString("make_name");
        String model = resultSet.getString("model_name");
        String vehicleType = resultSet.getString("vehicle_type");
        String color = resultSet.getString("vehicle_color");
        int odometer = resultSet.getInt("mileage");
        double price = resultSet.getInt("vehicle_price");
        String sold = resultSet.getString("SOLD");
        
        return new Vehicle(vin, year, make, model, vehicleType, color, odometer, price, sold);
    }
}
