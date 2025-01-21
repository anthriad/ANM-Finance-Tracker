package org.yourcompany.yourproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.Date;


public class Expenses extends Transaction
{
	  private double totalExpenses = 0;
//      private String authorizedBy = "";

  // primary constructor
	public Expenses(Date date, String description, float amount, String company, String authorizedBy)
	{
		super(date, company, description, amount);
		this.setAuthorizedBy(authorizedBy);
	}

  // overloaded constructor
  public Expenses(float amount, String description) 
  {
    this(new Date(), description, amount, "", "");
  }

  private void displayAllExpenses()
  {
    List<Map<String, Object>> expensesTable = new ArrayList<>(); // will be used for storing all the expenses DB returns
    String sql = "SELECT * FROM Expenses";

    try (Connection conn = DB.connect();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery())
    {
      int columnCount = rs.getMetaData().getColumnCount();

      while (rs.next())
      {
        Map<String, Object> row = new HashMap<>();
        for (int i = 1; i <= columnCount; i++)
        {
          String columnName = rs.getMetaData().getColumnName(i);
          row.put(columnName, rs.getObject(i));
        }
        expensesTable.add(row);
      }
    }
    catch (Exception ex)
    {
      ex.getStackTrace();
      System.out.println("An error occurred while retrieving the expenses table: " + ex.getMessage());
    }
  }

  private void deleteExpense(int id)
  {

    String sql = "DELETE FROM Expenses WHERE ID = ?";
    try (Connection conn = DB.connect();
         PreparedStatement ps = conn.prepareStatement(sql))
    {
      ps.setInt(1, id);
      ps.executeUpdate();

    }
    catch (Exception ex)
    {
      ex.getStackTrace();
      System.out.println("An error occurred while deleting the expense: " + ex.getMessage());
    }
  }

  private void addExpense(Date date, String description, float amount, String company, String authorizedBy)
  {
    String sql = "INSERT INTO Expenses (Date, Company, Description, Amount, AuthorizedBy) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DB.connect();
         PreparedStatement ps = conn.prepareStatement(sql))
    {
      boolean expenseAdded = false;

      ps.setDate(1, (java.sql.Date) date);
      ps.setString(2, company);
      ps.setString(3, description);
      ps.setFloat(4, amount);
      ps.setString(5, authorizedBy);

      ps.executeQuery(sql);

      expenseAdded = true;

      if (expenseAdded == true)
      {
        System.out.println("Expense added successfully!");
      }

    }
    catch (Exception ex)
    {
      ex.getStackTrace();
      System.out.println("An error occurred while adding the expense: " + ex.getMessage());
    }
  }

  private void editExpense(Date date, String description, float amount, String company, String authorizedBy)
  {

    String sql = "UPDATE Expenses SET Date = ?, Description = ?, Amount = ?, Company = ?, AuthorizedBy = ? WHERE ID = ?";

    try (Connection conn = DB.connect();
        PreparedStatement ps = conn.prepareStatement(sql))
    {
      boolean expenseUpdated = false;

      ps.setDate(1, (java.sql.Date) date);
      ps.setString(2, description);
      ps.setFloat(3, amount);
      ps.setString(4, company);
      ps.setString(5, authorizedBy);

      ps.executeUpdate();
      expenseUpdated = true;

      if (expenseUpdated == true)
      {
        System.out.println("Expenses updated successfully!");
      }
    }
    catch (Exception ex)
    {
      ex.getStackTrace();
      System.out.println("An error occurred while editing the expense: " + ex.getMessage());
    }
  }

  private void saveExpense()
  {
    try 
    {

    }
    catch (Exception ex)
    {
      
    }
  }

//  private double getTotalExpenses()
  private void getTotalExpenses()
  {

    String sql = "SELECT SUM(Amount) AS total_expenses FROM Expenses";

    try (Connection conn = DB.connect();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql))
    {
      if (rs.next())
      {
        totalExpenses = rs.getDouble("total_expenses");
      }
      else
      {
        System.out.println("No expenses found!");
      }

      System.out.println("Total expenses: " + totalExpenses);
//      return totalExpenses; // returning the total amount of expenses that have accrued
    }
    catch (Exception ex)
    {
      ex.getStackTrace();
      System.out.println("An error occurred while getting the expenses: " + ex.getMessage());
//      return 0;
    }
  }

}
