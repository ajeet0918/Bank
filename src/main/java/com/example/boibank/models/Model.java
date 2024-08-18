package com.example.boibank.models;

import com.example.boibank.view.Viewfactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static java.lang.Integer.parseInt;

public class Model {
    private static Model model;
    private final Viewfactory viewfactory;
    private final DatabaseDriver databaseDriver;
    // Client Data section
    private final Client client;
    private boolean clinetLoginSuccessFlag;
    // Admin Data section

    private boolean adminLoginSuccessFlag;
    private final ObservableList<Client> clients;

    private Model() {
        this.viewfactory = new Viewfactory();
        this.databaseDriver = new DatabaseDriver();
        // Client data section
        this.clinetLoginSuccessFlag = false;
        this.client = new Client("", "", "", "", "", "", null, null , null);

        // Admin data section

        this.adminLoginSuccessFlag = false;
        this.clients = FXCollections.observableArrayList();

    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public Viewfactory getViewfactory() {
        return viewfactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public boolean getClinetLoginSuccessFlag() {
        return this.clinetLoginSuccessFlag;
    }

    public void setClinetLoginSuccessFlag(boolean flag) {
        this.clinetLoginSuccessFlag = flag;
    }

    public void evaluateClientCred(String pAddress, String password) {
        SavingsAccount savingsAccount;
        CheckingAccount checkingAccount;
        ResultSet resultSet = null;
        try {
            resultSet = databaseDriver.getClientData(pAddress, password);
            System.out.println("ResultSet received: " + resultSet); // Debugging output
            if (resultSet != null && resultSet.next()) {
                // Client credentials are correct
                this.client.firstnameProperty().set(resultSet.getString("FirstName"));
                this.client.lastnameProperty().set(resultSet.getString("LastName"));
                this.client.payeeadressProperty().set(resultSet.getString("PayeeAddress"));
                LocalDate date = resultSet.getDate("Date").toLocalDate();
                this.client.datecreatedProperty().set(date);
                checkingAccount = gettCheckingAccount(pAddress);
                savingsAccount = getSavingsAccount(pAddress);

                this.client.checkingaccountProperty().set(checkingAccount);
                this.client.savingsaccountProperty().set(savingsAccount);
                this.clinetLoginSuccessFlag = true;
            } else {
                // Client credentials are incorrect
                this.clinetLoginSuccessFlag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            // Close the ResultSet in the finally block
            if (resultSet != null) {
                try {
                    resultSet.close();
                    //System.out.println("ResultSet closed successfully."); // Debugging output
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle the exception appropriately
                }
            }
        }
    }


    /*
// Admin method section
 */
    public boolean getAdminLoginSuccesFlag() {
        return this.adminLoginSuccessFlag;
    }

    public void setAdminLoginSuccessFlag(boolean adminLoginSuccessFlag) {
        this.adminLoginSuccessFlag = adminLoginSuccessFlag;
    }

    public void evaluateAdminCred(String username, String password) {
        try (ResultSet resultSet = databaseDriver.getAdminData(username, password)) {
            if (resultSet != null && resultSet.next()) {
                this.adminLoginSuccessFlag = true;
            } else {
                this.adminLoginSuccessFlag = false; // Optional: explicitly set flag to false if login fails
            }
        } catch (SQLException e) {
            // Handle specific SQL-related exceptions here
            e.printStackTrace(); // Or log the exception
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace(); // Or log the exception
        }
    }

    public ObservableList<Client> getClients() {
        return clients;
    }

    public void setClients() {
        clients.clear(); // Clear existing clients before updating
        ResultSet resultSet = null;
        try {
            resultSet = databaseDriver.getAllClientsData();
            while (resultSet.next()) {
                String fName = resultSet.getString("FirstName");
                String lName = resultSet.getString("LastName");
                String pAddress = resultSet.getString("PayeeAddress");
                String aadharNumber = resultSet.getString("AadharNumber"); // Retrieve Aadhar number from database
                String panNumber = resultSet.getString("PANNumber"); // Retrieve PAN number from database
                String mobileNumber = resultSet.getString("MobileNumber");// Retrieve Mobile number from database

                String dateStr = resultSet.getString("Date");
                LocalDate date = LocalDate.parse(dateStr); // Parse date string to LocalDate
                clients.add(new Client(fName, lName, pAddress, aadharNumber, panNumber, mobileNumber,null,null,date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ObservableList<Client> searchclient(String pAddress) {
        ObservableList<Client> searchresults = FXCollections.observableArrayList();
        ResultSet resultSet = null;

        try {
            resultSet = databaseDriver.searchclient(pAddress);

            // Check if there are any results before processing
            if (resultSet.next()) {
                CheckingAccount checkingAccount = gettCheckingAccount(pAddress);
                SavingsAccount savingsAccount = getSavingsAccount(pAddress);
                String fName = resultSet.getString("FirstName");
                String lName = resultSet.getString("LastName");
                String aadhar = resultSet.getString("AadharNo");
                String pan = resultSet.getString("PanNo");
                String mobile = resultSet.getString("MobileNo");
                String[] dateparts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateparts[0]), Integer.parseInt(dateparts[1]), Integer.parseInt(dateparts[2]));
                searchresults.add(new Client(fName, lName, pAddress, aadhar, pan, mobile, checkingAccount, savingsAccount, date));

            } else {
                // No results found, handle as needed
                System.out.println("No client found with address: " + pAddress);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return searchresults;
    }



    // utility methods section
    public CheckingAccount gettCheckingAccount(String pAddress) {
        CheckingAccount account = null;
        ResultSet resultSet = databaseDriver.getCheckingAccountData(pAddress);
        try {
            if (resultSet.next()) { // Check for data before processing
                String num = resultSet.getString("AccountNumber");
                int tLimit = (int) resultSet.getDouble("TransactionLimit");
                double balance = resultSet.getDouble("Balance");
                account = new CheckingAccount(pAddress, num, balance, tLimit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return account;
    }

    public SavingsAccount getSavingsAccount(String pAddress) {
        SavingsAccount account = null;
        ResultSet resultSet = databaseDriver.getSavingAccountData(pAddress);
        try {
            if (resultSet.next()) { // Check for data before processing
                String num = resultSet.getString("AccountNumber");
                int wLimit = (int) resultSet.getDouble("WithdrawalLimit");
                double balance = resultSet.getDouble("Balance");
                account = new SavingsAccount(pAddress, num, balance, wLimit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return account;
    }
}
