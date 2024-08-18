package com.example.boibank.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Client {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty payeeAddress;
    private final StringProperty aadharNumber; // New field for Aadhar number
    private final StringProperty panNumber; // New field for PAN number
    private final StringProperty mobileNumber; // New field for Mobile number
    private final ObjectProperty<Account> checkingAccount;
    private final ObjectProperty<Account> savingsAccount;
    private final ObjectProperty<LocalDate> dateCreated;

    public Client(String fname, String lName, String pAddress, String aadhar, String pan, String mobile, Account cAccount, Account sAccount, LocalDate date) {
        this.firstName = new SimpleStringProperty(this, "FirstName", fname);
        this.lastName = new SimpleStringProperty(this, "LastName", lName);
        this.payeeAddress = new SimpleStringProperty(this, "PayeeAddress", pAddress);
        this.aadharNumber = new SimpleStringProperty(this, "AadharNumber", aadhar);
        this.panNumber = new SimpleStringProperty(this, "PANNumber", pan);
        this.mobileNumber = new SimpleStringProperty(this, "MobileNumber", mobile);
        this.checkingAccount = new SimpleObjectProperty<>(this, "checkingaccounts", cAccount);
        this.savingsAccount = new SimpleObjectProperty<>(this, "savingsaccounts", sAccount);
        this.dateCreated = new SimpleObjectProperty<>(this, " date", date);
    }


    public StringProperty firstnameProperty() {
        return firstName;
    }

    public StringProperty lastnameProperty() {
        return lastName;
    }

    public StringProperty payeeadressProperty() {
        return payeeAddress;
    }

    public StringProperty aadharNumberProperty() {
        return aadharNumber;
    }

    public StringProperty panNumberProperty() {
        return panNumber;
    }

    public StringProperty mobileNumberProperty() {
        return mobileNumber;
    }

    public ObjectProperty<Account> checkingaccountProperty() {
        return checkingAccount;
    }

    public ObjectProperty<Account> savingsaccountProperty() {
        return savingsAccount;
    }

    public ObjectProperty<LocalDate> datecreatedProperty() {
        return dateCreated;
    }
}
