package com.ps;

import org.apache.commons.dbcp2.BasicDataSource;

public class UserInterface {
    // Change UserInterface in order to save to the Database instead of writing to the file.
    private static BasicDataSource basicDataSource;
    
    public static void init(String[] args) {
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/dealership_db");
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(args[0]);
        basicDataSource.setPassword(args[1]);
    }
    
    public static void display(String[] args) {
        init(args);
    }
}
