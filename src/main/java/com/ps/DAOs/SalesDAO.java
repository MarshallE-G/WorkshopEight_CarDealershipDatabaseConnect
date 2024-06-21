package com.ps.DAOs;

import com.ps.DAOs.interfaces.ContractInt;
import com.ps.models.Contract;
import com.ps.models.SalesContract;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDAO implements ContractInt {
    private BasicDataSource basicDataSource;
    
    public SalesDAO(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }
    
    @Override
    public void saveContract(Contract contract) {
        SalesContract salesContract = ((SalesContract) contract);
        String financeOption;
        
        try (
                Connection connection = this.basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO sales_contracts(" +
                                "`contract_date`, " +
                                "`customer_name`, " +
                                "`customer_email`, " +
                                "`VIN`, " +
                                "`vehicle_price`, " +
                                "`sales_tax_amount`, " +
                                "`recording_fee`, " +
                                "`processing_fee`, " +
                                "`finance_option`, " +
                                "`total_price`, " +
                                "`monthly_payment`" +
                                ") " +
                                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );
        ) {
            preparedStatement.setString();
            preparedStatement.setString();
            preparedStatement.setString();
            preparedStatement.setInt();
            preparedStatement.setFloat();
            preparedStatement.setFloat();
            preparedStatement.setFloat();
            preparedStatement.setFloat();
            
            if (salesContract.getFinanceOption()) {
                financeOption = "YES";
            } else {
                financeOption = "NO";
            }
            
            preparedStatement.setString();
            preparedStatement.setFloat();
            preparedStatement.setFloat();
            
            preparedStatement.executeUpdate();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }
}
