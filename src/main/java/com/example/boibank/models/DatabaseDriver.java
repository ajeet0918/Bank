package com.example.boibank.models;

import java.sql.*;
import java.time.LocalDate;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

public class DatabaseDriver {

    private Connection conn;

    public DatabaseDriver() {
        try {
            // Update the database URL, username, and password as per your MySQL setup
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "ajeet157");
            this.conn.createStatement().executeUpdate("USE bank");

        } catch (SQLException e) {
            // Properly handle the exception based on your application's requirementsz
            e.printStackTrace();
        }
    }

    public ResultSet getClientData(String pAddress , String  password){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement= this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM  clients WHERE PayeeAddress ='"+pAddress+"' AND Password ='"+password+"';");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    // Add methods for admin section, if needed

    public ResultSet getAdminData(String username , String password){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM  admins WHERE  Username = '"+username+"'AND Password = '"+password+"';");
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public void createClient(String fName, String lName, String pAddress, String password, String aadharNumber, String panNumber, String mobileNumber, LocalDate date) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO clients (FirstName, LastName, PayeeAddress, Password, AadharName, PANNumber, MobileNumber, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, fName);
            statement.setString(2, lName);
            statement.setString(3, pAddress);
            statement.setString(4, password);
            statement.setString(5, aadharNumber);
            statement.setString(6, panNumber);
            statement.setString(7, mobileNumber);
            statement.setDate(8, Date.valueOf(date));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public void createCheckingAccount(String owner, String number, double tLimit, double balance) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO checkingaccounts (Owner, AccountNumber, transactionLimit, Balance) VALUES (?, ?, ?, ?)");
            statement.setString(1, owner);
            statement.setString(2, number);
            statement.setDouble(3, tLimit);
            statement.setDouble(4, balance);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createSavingAccount(String owner, String number, double wLimit, double balance) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO savingaccounts (Owner, AccountNumber, WithdrawalLimit, Balance) VALUES (?, ?, ?, ?)");
            statement.setString(1, owner);
            statement.setString(2, number);
            statement.setDouble(3, wLimit);
            statement.setDouble(4, balance);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public ResultSet getAllClientsData(){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT* FROM clients ");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
}

public ResultSet searchclient(String pAddress){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement=this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT*FROM clients WHERE PayeeAddress = '"+pAddress+"';");
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
}

public void depositesavings(String pAddress, double amount){
         Statement statement = null;
        try {
            this.conn.createStatement();
            statement.executeUpdate("UPDATE savingaccounts SET Balance = "+amount+" WHERE Owner = '"+pAddress+"' ;");
        }catch (Exception e){
            e.printStackTrace();
        }
}

    public int getLastAutoIncrementValue() {
        String tableName = "clients";
        int id = 0;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "ajeet157");
             Statement statement = conn.createStatement()) {

            String query = "SELECT MAX(ClientID) FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public ResultSet getCheckingAccountData(String pAddress){
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT*FROM checkingaccounts WHERE Owner = '"+pAddress+"';");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getSavingAccountData(String pAddress){
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT*FROM savingaccounts WHERE Owner = '"+pAddress+"';");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }





    // Close connection method
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            // Properly handle the exception based on your application's requirements
            e.printStackTrace();
        }
    }

}
