package com.example.boibank.models;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CheckingAccount extends  Account{

    //The number of transactions a client is allowed to do per day
    private final IntegerProperty transactionlimit;
    public CheckingAccount(String owner , String accountNumber , double   balance , int tlimit) {
        super(owner, accountNumber, balance);
        this.transactionlimit = new SimpleIntegerProperty(this, " Transaction Limit ", tlimit);
    }

    public IntegerProperty TransactionlimitProperty(){return transactionlimit;}

    @Override
    public String toString(){
        return accountNumberProperty().get();
    }
}
