package org.example;

public class Income extends Transaction {
    public Object getCategory() {
        return category;
    }
    private final Object category;

    public Income(double amount, Object category) {
        super(amount);
        this.category = category;
    }
}