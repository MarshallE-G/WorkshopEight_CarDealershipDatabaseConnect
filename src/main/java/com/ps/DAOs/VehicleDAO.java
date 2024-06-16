package com.ps.DAOs;

import com.ps.DAOs.interfaces.VehicleInt;
import com.ps.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
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
        List<Vehicle> vehicles = new ArrayList<>();
    
        try (
                Connection connection = basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE make_name = ? AND model_name = ?"
                );
        ) {
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);
        
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
    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> vehicles = new ArrayList<>();
    
        try (
                Connection connection = basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE year >= ? AND year <= ?"
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
    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
    
        try (
                Connection connection = basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE vehicle_color = ?"
                );
        ) {
            preparedStatement.setString(1, color);
        
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
    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> vehicles = new ArrayList<>();
    
        try (
                Connection connection = basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE mileage >= ? AND mileage <= ?"
                );
        ) {
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
        
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
    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> vehicles = new ArrayList<>();
    
        try (
                Connection connection = basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM vehicles WHERE vehicle_type = ?"
                );
        ) {
            preparedStatement.setString(1, vehicleType);
        
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
    public void addVehicle(Vehicle vehicle) {
        try (
                Connection connection = this.basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO vehicles(" +
                                "`VIN`, " +
                                "`year`, " +
                                "`make_name`, " +
                                "`model_name`, " +
                                "`vehicle_type`, " +
                                "`vehicle_color`, " +
                                "`mileage`, " +
                                "`vehicle_price`, " +
                                "`SOLD`) " +
                                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);"
                );
        ) {
            preparedStatement.setInt(1, vehicle.getVin());
            preparedStatement.setInt(2, vehicle.getYear());
            preparedStatement.setString(3, vehicle.getMake());
            preparedStatement.setString(4, vehicle.getModel());
            preparedStatement.setString(5, vehicle.getVehicleType());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setInt(7, vehicle.getOdometer());
            preparedStatement.setDouble(8, vehicle.getPrice());
            preparedStatement.setString(9, vehicle.getSold());
            preparedStatement.executeUpdate();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }
    
    @Override
    public void removeVehicle(Vehicle vehicle) {
    
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
