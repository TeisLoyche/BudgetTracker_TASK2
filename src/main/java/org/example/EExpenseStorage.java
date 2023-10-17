package org.example;

public enum EExpenseStorage {
    BILLS, GROCERIES, AMUSEMENT, TRAVEL;

    /* This method receives an integer from the addExpense method,
    which allows the user to select a category for to the Expense-object, based on the ordinal value of the enum. */
    public static EExpenseStorage ordinal(int choice) {

        if (choice == 1) {
            return BILLS;
        }
        else if (choice == 2) {
            return GROCERIES;
        }
        else if (choice == 3) {
            return AMUSEMENT;
        }
        else{
            return TRAVEL;
        }
    }
}