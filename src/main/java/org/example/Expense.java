package org.example;

public class Expense extends Transaction {
    public Object getCategory() {
        return category;
    }
    private final Object category;

    public Expense(double amount, Object category) {
        super(amount - amount - amount);
        this.category = category;
    }
}