package org.example;

import java.util.Date;

public class Transaction {
    private double amount;
    private Date date;

    //Transaction constructor.
    public Transaction(double amount) {
        this.amount = amount;
        this.date = new Date();
    }

    //Getters for the attributes of a Transaction-object---------------------------------------------------------------
    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
    //-----------------------------------------------------------------------------------------------------------------

    //Override for Transaction constructor.
    @Override
    public String toString() {
        return "Transaction: " +
                amount + "kr " +
                "Date: " + date;
    }
}