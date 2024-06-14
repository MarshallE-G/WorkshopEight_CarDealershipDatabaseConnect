package com.ps.DAOs;

import org.apache.commons.dbcp2.BasicDataSource;

public class VehicleDAO {
    private BasicDataSource basicDataSource;
    
    public VehicleDAO(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }
    
//    By price range
//    By make/model
//    By year range
//    By color
//    By mileage range
//    By type

}
