package org.yourcompany.yourproject;

import java.time.LocalDate;
import java.util.Scanner;
import java.sql.Date;

/* MainMenu: This class will serve as the main interface (temporarily until frontend is established) for the project as
this will serve as the menu that the user will interact with primarily to manage the finances for Adorned N Mercy. */

public class MainMenu {

    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args)
    {
        System.out.println("\nWELCOME TO THE ADORNED N MERCY FINANCIAL TRACKER!\n");
        System.out.println("Here you can manage your financial needs and tracking for the company.");
        System.out.println("*******************************************************************************************");


        while (true)
        {
            // Prompting the user to pick from the menu options
            System.out.println("""
                \nMAIN MENU
                **************
                Select an option from the menu below:
                    1) Expenses
                    2) Revenue
                    3) Deposits\s
                    4) Net Profit\s
                    5) Exit\n\n Enter your choice:""");

            int userSelection = userInput.nextInt();

            switch (userSelection)
            {
                // Expenses Menu
                case 1:
                    try
                    {
                        System.out.println("""
                    \nEXPENSES MENU
                    **************
                    Select from the following:
                        1) Show all expenses\s
                        2) Get total amount of all expenses\s
                        3) Add an expense\s
                        4) Edit an expense\s
                        5) Delete an expense\s
                        6) Exit\n\n Enter your choice:""");

                        int expensesSelection = userInput.nextInt();

                        switch (expensesSelection)
                        {
                            case 1:
                                Expenses.displayAllExpenses();
                            case 2:
                                displayTotalExpenses();
                            case 3:
                                addExpense();
                            case 4:
                                editExpense();
                            case 5:
                                deleteExpense();
                            // exit option - returning to the main menu
                            case 6:
                                System.out.println("\nReturning to the main menu...");
                                continue;

                            default:
                                System.out.println("\nI'm sorry, that option is not available. Please try again.\n");
                        }


                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }

                    // Revenue Menu
                case 2:
                    try
                    {
                        System.out.println("""
                    REVENUE MENU
                    **************
                    Select from the following:
                        1) Show all revenue\s
                        2) Show specific month revenue\s
                        3) Exit\n""");

                        int revenueSelection = userInput.nextInt();

                        switch (revenueSelection)
                        {
                            case 1:

                            case 2:

                                // exit option - returning to the main menu
                            case 3:
                                System.out.println("Returning to the main menu...");
                                break;

                            default:
                                System.out.println("\nI'm sorry, that option is not available. Please try again.");
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }

                    // Deposits Menu
                case 3:
                    try
                    {
                        System.out.println("""
                    DEPOSITS MENU
                    **************
                    Select from the following:
                        1) Show all deposits\s
                        2) Add a deposit\s
                        3) Edit a deposit\s
                        4) Delete a deposit\s
                        5) Exit\n""");

                        int depositsSelection = userInput.nextInt();

                        switch (depositsSelection)
                        {
                            case 1:

                            case 2:

                            case 3:

                            case 4:

                                // exit option - returning to the main menu
                            case 5:
                                System.out.println("Returning to the main menu...");
                                break;

                            default:
                                System.out.println("\nI'm sorry, that option is not available. Please try again.");
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }

                    // Net Profits Menu
                case 4:
                    try
                    {
                        System.out.println("""
                    NET PROFIT MENU
                    **************
                    Select from the following:
                        1) Show all net profit\s
                        2) Show specific month revenue\s
                        3) Exit\n""");

                        int net_profitSelection = userInput.nextInt();

                        switch (net_profitSelection)
                        {
                            case 1:

                            case 2:

                                // exit option - returning to the main menu
                            case 3:
                                System.out.println("Returning to the main menu...");
                                break;

                            default:
                                System.out.println("\nI'm sorry, that option is not available. Please try again.");
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }

                    // exit option
                case 5:
                    try
                    {
                        System.out.println("Thank you for stopping by! We'll see you next time!");
                        return;
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }

                default:
                    System.out.println("\nI'm sorry, that option is not available. Please try again.");
            }
        }
    }

    private static void followUpQuestion()
    {
        System.out.println("Is there anything else you would like to do? (Y/N)");
        String answer = userInput.nextLine();

        switch (answer)
        {
            case "Y":
                break;
            case "N":
                return;
            default:
                System.out.println("\nI'm sorry, that option is not available. Please try again.");
        }
    }

///
/// EXPENSE FUNCTIONS
///
    private static void addExpense()
    {
        try {
            System.out.println("Attempting to add an expense...");

            // setting up the date in the sql format
            LocalDate today = LocalDate.now();
            Date todaysDate = Date.valueOf(today);

            System.out.println("Enter the name of company charging the expense: ");
            String companyName = userInput.nextLine();

            System.out.println("Enter a short description of the expense: ");
            String expenseDescription = userInput.nextLine();

            System.out.println("Enter the amount of the expense: ");
            double expenseAmount = userInput.nextDouble();

            System.out.println("Enter your initials: ");
            String authorizedUser = userInput.nextLine();

            if (authorizedUser.equals("AR") || authorizedUser.equals("AG") || authorizedUser.equals("NA") || authorizedUser.equals("MS")) {

                int executeAdd = Expenses.addExpense(todaysDate, expenseDescription, expenseAmount, companyName, authorizedUser);

                if (executeAdd == 1)
                {
                    System.out.println("Expense was added successfully.");
                }
                else if (executeAdd == 0)
                {
                    System.out.println("An issue occurred while trying to delete the expense.");
                }

                followUpQuestion();
            }
            else
            {
                System.out.println("You are not authorized to add an expense! Unable to add expense.");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    private static void editExpense()
    {
        try
        {
            System.out.println("Attempting to edit an expense...");

            System.out.println("Enter the ID of the expense you would like to edit: ");
            int expenseID = userInput.nextInt();

            int executeEdit = Expenses.editExpense(expenseID);

            if (executeEdit == 1)
            {
                System.out.println("Expense was edited successfully.");
            }
            else if (executeEdit == 0)
            {
                System.out.println("An issue occurred while trying to edit the expense with ID " + expenseID + ".");
            }

            followUpQuestion();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteExpense()
    {
        try
        {
            System.out.println("Attempting to delete an expense...");

            System.out.println("Enter the ID of the expense you would like to delete: ");
            int expenseID = userInput.nextInt();

            int executeDelete = Expenses.deleteExpense(expenseID);

            if (executeDelete == 1)
            {
                System.out.println("Expense with ID " + expenseID + " was successfully deleted.");
            }
            else if (executeDelete == 0)
            {
                System.out.println("An issue occurred while trying to delete the expense with ID " + expenseID + ".");
            }
            followUpQuestion();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void displayTotalExpenses()
    {
        System.out.println("Retrieving total amount of expenses... ");

        Expenses.getTotalExpenses();

        followUpQuestion();

    }
}
