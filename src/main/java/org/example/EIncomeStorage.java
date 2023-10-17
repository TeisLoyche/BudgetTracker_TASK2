package org.example;

public enum EIncomeStorage {
    SALARY, COMMISSION, OTHER;

    /* This method receives an integer from the addIncome method,
    which allows the user to select a category for to the Income-object, based on the ordinal value of the enum. */
    public static EIncomeStorage ordinal(int choice) {

        if (choice == 1) {
            return SALARY;
        }
        else if (choice == 2) {
            return COMMISSION;
        }
        else{
            return OTHER;
        }
    }
}