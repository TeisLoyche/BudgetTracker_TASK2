package org.example;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IncomeStorage {
    //Creates an arraylist for storing income.
    private ArrayList<Income> incomeList = new ArrayList<>();

    private Gson gson = new Gson();

    private double total;

    //A getter for total to be able to print out the total sum of the income-arraylist.
    public double getTotal() {
        return total;
    }

    //METHODS----------------------------------------------------------------------------------------------------------

    //Method for adding an Income-object to the income-arraylist.
    public void addIncomeToArraylist(Income income) {
        incomeList.add(income);
        System.out.println(income.getAmount() + " " + income.getDate() + " " + income.getCategory() +
                " was successfully added.");
    }

    //Method for listing the arraylist of income along with each income index position.
    public void printIncomeArrayList() {
        /* Checks to see if the income-arraylist is empty, in that case let the user know,
        otherwise print out the list of saved income.*/
        if (incomeList.isEmpty()) {
            System.out.println("You haven't added any income.");
        } else {
            for (int i = 0; i < incomeList.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + incomeList.get(i).getAmount() + " " + incomeList.get(i).getDate()
                        + " " + incomeList.get(i).getCategory());
            }
        }
    }

    //Method for checking if income-arraylist is empty before continuing with next code.
    public void ifIncomeArraylistIsEmpty() throws IOException {
        if (incomeList.isEmpty()) {
            System.out.println("You haven't added any income.");
            BudgetTracker.incomeMenu();
        }
    }

    //Method for removing an Income-object from the income-arraylist.
    public void removeIncomeFromArrayList(int choice) throws IOException {
        System.out.println("Income was successfully removed!");
        incomeList.remove(choice - 1);
        writeIncomeArrayToFile();
    }

    /* Method for changing an income in the income-arraylist.
    Receives an integer which determines which Income-Object in the list to replace based on its index position.
    Receives a new Income-object to replace the previous Income-object.
     */
    public void changeIncome(int choice, Income income) throws IOException {
        incomeList.set(choice - 1, income);
        writeIncomeArrayToFile();
    }

    //Method for gathering all Income-objects amounts from the arraylist and putting them into the variable "total".
    public void totalOfIncomeArray() {
        double sum = 0;
        for (Income i : incomeList) {
            sum += i.getAmount();
        }
        this.total = sum;
    }

    //WRITING AND READING EXPENSES TO/FROM FILE------------------------------------------------------------------------

    //This method writes the income-arraylist to a file.
    public void writeIncomeArrayToFile() throws IOException {
        FileWriter fw = new FileWriter("src/main/incomes.json");
        gson.toJson(incomeList, fw);
        fw.flush();
    }

    /* This method reads the json file to an array, then adds all saved income back into the income-arraylist,
    basically making a copy of the old arraylist which allows user to add more income without overwriting the
    previous ones. Not the greatest solution, but I just couldn't get the f*****g MAPS to work.
     */
    public void readIncomeFile() throws IOException {
        try {
            Income[] incomes = null;

            FileReader fr = new FileReader("src/main/incomes.json");
            incomes = gson.fromJson(fr, Income[].class);

            for (int i = 0; i < incomes.length; i++) {
                incomeList.add(incomes[i]);
            }
        } catch (NullPointerException e) { //If there are no values stored in the array, catch the null exception.
        }
    }
}