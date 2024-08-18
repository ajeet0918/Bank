package com.example.boibank.models;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public abstract class Account {
    private final StringProperty owner;
    private final StringProperty accountNumber;
    private DoubleProperty balance;
    public Account(String owner , String accountNumber , double  balance  ){
        this.owner = new SimpleStringProperty(this , "Owner" , owner);
        this.accountNumber = new SimpleStringProperty(this, "AccountNumber", accountNumber);
        this. balance = new SimpleDoubleProperty(this, "Balance", balance);
    }
    public StringProperty ownerProperty(){return owner;}
    public StringProperty accountNumberProperty(){return accountNumber;}
    public DoubleProperty balanceProperty(){return balance;}
}
