package org.example;

import java.io.IOException;
import java.util.Scanner;

public class BudgetTracker {

    //Scanner for input.
    static Scanner input = new Scanner(System.in);

    //Creates objects of IncomeStorage and ExpenseStorage so their methods can be called.
    static IncomeStorage incomeStorage = new IncomeStorage();
    static ExpenseStorage expenseStorage = new ExpenseStorage();

    //Creates booleans for the different menus to loop.
    static boolean isRunningIncomeMenu = true;
    static boolean isRunningMainMenu = true;
    static boolean isRunningExpenseMenu = true;


    //MAIN METHOD where the program runs-------------------------------------------------------------------------------
    public static void main(String[] args) throws IOException {

        //Program starts by reading in all stored incomes and expenses from their respective files.
        incomeStorage.readIncomeFile();
        expenseStorage.readExpenseFile();

        //BudgetTracker starts up.
        budgetTrackerStart();
    }

    //INCOME METHODS---------------------------------------------------------------------------------------------------

    //Method for creating an income and adding it to the income-arraylist.
    public static void addIncome() throws IOException {
        //User inputs amount of income.
        System.out.println("Please enter the amount of your income: ");
        double amount = input.nextDouble();

        //Writes out all enum-categories with corresponding ordinal value.
        int a = 1;
        System.out.println("Please select which category this income belongs to: ");
        for (EIncomeStorage e : EIncomeStorage.values()) {
            System.out.println("[" + a + "]" + e.toString());
            a++;
        }
        //Accesses the Income Enum ordinal method and returns the corresponding value to the category variable.
        int choice = input.nextInt();
        EIncomeStorage category = EIncomeStorage.ordinal(choice);

        //Creates a new Income-object with provided amount and category.
        Income income = new Income(amount, category);

        //Adds Income-object to income-arraylist.
        incomeStorage.addIncomeToArraylist(income);
        incomeStorage.writeIncomeArrayToFile();
    }

    //Method for changing an existing income.
    public static void changeIncome() throws IOException {
        //IF income-arraylist is empty: Let user know and return to Expense-menu.
        incomeStorage.ifIncomeArraylistIsEmpty();

        //ELSE

        //Prints out income-arraylist.
        incomeStorage.printIncomeArrayList();

        //User selects which income to change in the list.
        System.out.println("Please select which income you want to change: ");
        int intChoice = input.nextInt();

        //User inputs new amount for the selected income.
        System.out.println("Please enter the new amount: ");
        double amount = input.nextDouble();

        //User selects new category for the selected income.
        int a = 1;
        System.out.println("Please select which category this income belongs to: ");
        for (EIncomeStorage e : EIncomeStorage.values()) {
            System.out.println("[" + a + "]" + e.toString());
            a = a + 1;
        }
        int choice = input.nextInt();
        EIncomeStorage category = EIncomeStorage.ordinal(choice);

        //Creates a new Income-object to replace the old Income-object.
        Income income = new Income(amount, category);

        //The new Income-object is sent to the method for replacement.
        incomeStorage.changeIncome(intChoice, income);
    }

    //EXPENSE METHODS--------------------------------------------------------------------------------------------------

    //Method for creating a new expense and adding it to the expense-arraylist.
    public static void addExpense() throws IOException {
        //User inputs amount of the expense.
        System.out.println("Please enter the amount of your expense: ");
        double amount = input.nextDouble();

        //Writes out all enum-categories with corresponding ordinal value.
        int a = 1;
        System.out.println("Please select which category this income belongs to: ");
        for (EExpenseStorage e : EExpenseStorage.values()) {
            System.out.println("[" + a + "]" + e.toString());
            a++;
        }
        //User selects category based on ordinal value.
        int choice = input.nextInt();

        //Accesses the Expense Enum ordinal method and returns the corresponding value to the category variable.
        EExpenseStorage category = EExpenseStorage.ordinal(choice);

        //Creates a new Expense-object with provided amount and category.
        Expense expense = new Expense(amount, category);

        //Adds Expense-object to income-arraylist.
        expenseStorage.addExpenseToArrayList(expense);
        expenseStorage.writeExpenseArrayToFile();
    }

    //Method for changing an existing expense.
    public static void changeExpense() throws IOException {
        //IF expense-arraylist is empty: Let user know and return to Expense-menu.
        expenseStorage.ifExpenseArraylistIsEmpty();

        //ELSE

        //Prints out expense-arraylist.
        expenseStorage.printExpenseArrayList();

        //User selects which expense to change in the list.
        System.out.println("Please select which expense you want to change: ");
        int intChoice = input.nextInt();

        //User inputs new amount for the selected expense.
        System.out.println("Please enter the new amount: ");
        double amount = input.nextDouble();

        //User selects new category for the selected expense.
        int a = 1;
        System.out.println("Please select which category this income belongs to: ");
        for (EExpenseStorage e : EExpenseStorage.values()) {
            System.out.println("[" + a + "]" + e.toString());
            a = a + 1;
        }
        int choice = input.nextInt();
        EExpenseStorage category = EExpenseStorage.ordinal(choice);

        //Creates a new Expense-object to replace the old Expense-object.
        Expense expense = new Expense(amount, category);

        //The new Expense-object is sent to the method for replacement.
        expenseStorage.changeExpense(intChoice, expense);
    }

    //MENUS------------------------------------------------------------------------------------------------------------

    //Main menu.
    public static void mainMenu() throws IOException {

        while (isRunningMainMenu) {
            System.out.println("Welcome to BudgetTracker!");
            System.out.println("[1] - INCOME.");
            System.out.println("[2] - EXPENSES.");
            System.out.println("[3] - Total budget.");
            System.out.println("[4] - Exit.");

            try {
                int selection = input.nextInt();

                switch (selection) {
                    case 1:
                        incomeMenu();
                        break;
                    case 2:
                        expenseMenu();
                        break;
                    case 3:
                        //Methods for looping all Income and Expense amounts into a corresponding "total" variable.
                        incomeStorage.totalOfIncomeArray();
                        expenseStorage.totalOfExpenseArray();

                        //Prints out all Income and Expenses.
                        System.out.println("All your income: ");
                        incomeStorage.printIncomeArrayList();
                        System.out.println("[Income total: " + incomeStorage.getTotal() + "]");
                        System.out.println(" ");
                        System.out.println("-----------------");

                        System.out.println("All your expenses: ");
                        expenseStorage.printExpenseArrayList();
                        System.out.println("[Expenses total: " + expenseStorage.getTotal() + "]");
                        System.out.println(" ");
                        System.out.println("-----------------");

                        //Prints out total of all stored Income-objects minus the total of all stored Expense-objects.
                        //Because the Expense-objects have a negative amount, the plus operator is needed.
                        System.out.println("Your total budget after income - expenses is: " +
                                (expenseStorage.getTotal() + incomeStorage.getTotal()));
                        System.out.println("-----------------");
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please select between options 1 - 4!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a number.");
                input.next();
            }
        }
    }

    //Income menu.
    public static void incomeMenu() throws IOException {
        while (isRunningIncomeMenu) {
            System.out.println("----INCOME----");
            System.out.println("[1] - Add income.");
            System.out.println("[2] - Change existing income.");
            System.out.println("[3] - Remove income.");
            System.out.println("[4] - See all of your income.");
            System.out.println("----------------");
            System.out.println("[5] - Return to main menu.");

            try {
                int selection = input.nextInt();

                switch (selection) {
                    case 1:
                        addIncome();
                        break;
                    case 2:
                        changeIncome();
                        break;
                    case 3:
                        /* First checks to see if there is any income saved.
                        If not, let user know and return to Income-menu. */
                        incomeStorage.ifIncomeArraylistIsEmpty();

                        //Methods for removing an income.
                        System.out.println("Please select which income you'd like to remove: ");
                        incomeStorage.printIncomeArrayList();
                        int choice = input.nextInt();
                        incomeStorage.removeIncomeFromArrayList(choice);
                        break;
                    case 4:
                        incomeStorage.printIncomeArrayList();
                        break;
                    case 5:
                        mainMenu();
                        break;
                    default:
                        System.out.println("Please select between options 1 - 5!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a number.");
                input.next();
            }
        }
    }

    //Expense menu.
    public static void expenseMenu() throws IOException {
        while (isRunningExpenseMenu) {
            System.out.println("----EXPENSES----");
            System.out.println("[1] - Add expense.");
            System.out.println("[2] - Change existing expense.");
            System.out.println("[3] - Remove expense.");
            System.out.println("[4] - See all of your expenses.");
            System.out.println("----------------");
            System.out.println("[5] - Return to main menu.");

            try {
                int selection = input.nextInt();

                switch (selection) {
                    case 1:
                        addExpense();
                        break;
                    case 2:
                        changeExpense();
                        break;
                    case 3:
                        /* First checks to see if there are any expenses saved.
                        If not, let user know and return to Expense-menu. */
                        expenseStorage.ifExpenseArraylistIsEmpty();

                        //Methods for removing an expense.
                        System.out.println("Please select which expense you'd like to remove: ");
                        expenseStorage.printExpenseArrayList();
                        int choice = input.nextInt();
                        expenseStorage.removeExpenseFromArrayList(choice);
                        break;
                    case 4:
                        expenseStorage.printExpenseArrayList();
                        break;
                    case 5:
                        mainMenu();
                        break;
                    default:
                        System.out.println("Please select between options 1 - 5!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a number.");
                input.next();
            }
        }
    }

    //START OF PROGRAM-------------------------------------------------------------------------------------------------

    //User creates a user in order to log in and access the main menu.
    public static void budgetTrackerStart() throws IOException {
        System.out.println("Welcome to BudgetTracker!");
        System.out.println("Please enter your first name: ");
        String firstName = input.nextLine();

        System.out.println("Please enter your last name:");
        String lastName = input.nextLine();

        User user = new User(firstName, lastName);
        logIn(user);
    }

    //Prints out user data and runs the main menu.
    public static void logIn(User user) throws IOException {
        System.out.println("Hello " + user);
        System.out.println("BudgetTracker is loading...");
        mainMenu();
    }
}