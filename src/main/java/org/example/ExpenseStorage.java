package org.example;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExpenseStorage {
    //Creates an arraylist for storing expenses.
    private ArrayList<Expense> expenseList = new ArrayList<>();

    private Gson gson = new Gson();

    private double total;

    //A getter for total to be able to print out the total sum of the expense-arraylist.
    public double getTotal() {
        return total;
    }

    //METHODS----------------------------------------------------------------------------------------------------------

    //Method for adding an expense to the expense-arraylist.
    public void addExpenseToArrayList(Expense expense) throws IOException {
        expenseList.add(expense);
        System.out.println(expense.getAmount() + " " + expense.getDate() + " " + expense.getCategory() +
                " was successfully added.");
    }

    //Method for listing the arraylist of expenses along with each expense index position.
    public void printExpenseArrayList() {
        /* Checks to see if the expense-arraylist is empty, in that case let the user know,
        otherwise print out the list of saved expenses.*/
        if (expenseList.isEmpty()) {
            System.out.println("You haven't added any expenses.");
        } else {
            for (int i = 0; i < expenseList.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + expenseList.get(i).getAmount() + " " +
                        expenseList.get(i).getDate() + " " + expenseList.get(i).getCategory());
            }
        }
    }

    //Method for checking if expense-arraylist is empty before continuing with next code.
    public void ifExpenseArraylistIsEmpty() throws IOException {
        if (expenseList.isEmpty()) {
            System.out.println("You haven't added any expenses.");
            BudgetTracker.expenseMenu();
        }
    }

    //Method for removing an Expense-object from the expense-arraylist.
    public void removeExpenseFromArrayList(int choice) throws IOException {
        System.out.println("Expense was successfully removed!");
        expenseList.remove(choice - 1);
        writeExpenseArrayToFile();
    }

    /* Method for changing an expense in the expense-arraylist.
    Receives an integer which determines which Expense-Object in the list to replace based on its index position.
    Receives a new Expense-object to replace the previous Expense-object.
     */
    public void changeExpense(int choice, Expense expense) throws IOException {
        expenseList.set(choice - 1, expense);
        writeExpenseArrayToFile();
    }

    //Method for gathering all Expense-objects amounts from the arraylist and putting them into the variable "total".
    public void totalOfExpenseArray() {
        double sum = 0;
        for (Expense e : expenseList) {
            sum += e.getAmount();
        }
        this.total = sum;
    }

    //WRITING AND READING EXPENSES TO/FROM FILE------------------------------------------------------------------------

    //This method writes the expense-arraylist to a json file.
    public void writeExpenseArrayToFile() throws IOException {
        FileWriter fw = new FileWriter("src/main/expenses.json");
        gson.toJson(expenseList, fw);
        fw.flush();
    }

    /* This method reads the json file to an array, then adds all saved expenses back into the expense-arraylist,
    basically making a copy of the old arraylist which allows user to add more expenses without overwriting the
    previous ones. Not the greatest solution, but I just couldn't get the f*****g MAPS to work.
     */
    public void readExpenseFile() throws IOException {
        try {
            Expense[] expenses = null;

            FileReader fr = new FileReader("src/main/expenses.json");
            expenses = gson.fromJson(fr, Expense[].class);

            for (int i = 0; i < expenses.length; i++) {
                expenseList.add(expenses[i]);
            }
        } catch (NullPointerException e) { //If there are no values stored in the array, catch the null exception.
        }
    }
}