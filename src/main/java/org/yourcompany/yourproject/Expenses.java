package org.yourcompany.yourproject;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;


public class Expenses extends Transaction
{
	  private static BigDecimal totalExpenses = BigDecimal.valueOf(0.00);
//      private String authorizedBy = "";

  // primary constructor
  public Expenses(Date date, String description, BigDecimal amount, String company, String authorizedBy)
  {
		super(date, company, description, amount);
		this.setAuthorizedBy(authorizedBy);
  }

  // overloaded constructor
  public Expenses(BigDecimal amount, String description)
  {
    this(new Date(), description, amount, "", "");
  }

  public Expenses(java.util.Date date, String company, String description, BigDecimal amount) {
    super(date, company, description, amount);
  }

  public static void displayAllExpenses()
  {

    System.out.println("Retrieving all your expenses. Please wait...");

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

  public static int deleteExpense(int id)
  {
    String sql = "DELETE FROM expenses WHERE expense_id = ?";
    try (Connection conn = DB.connect())
    {
      conn.setAutoCommit(false);
      try (PreparedStatement ps = conn.prepareStatement(sql))
      {
        ps.setInt(1, id);
        ps.executeUpdate();

        conn.commit();
        return 1;
      }
      catch (Exception ex) {
        conn.rollback();
        System.out.println("An error occurred while deleting the expense: " + ex.getMessage());
        ex.printStackTrace();
        return 0;
      }
    }
    catch (Exception ex)
    {
      System.out.println("Database connection error: " + ex.getMessage());
      return 0;
    }
  }

  public static int addExpense(Date date, String description, BigDecimal amount, String company, String authorizedBy) {
    String sql = "INSERT INTO Expenses (Date, Company, Description, Amount, AuthorizedBy) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DB.connect()) {
      conn.setAutoCommit(false); // starting the transaction

      try (PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setDate(1, new java.sql.Date(date.getTime()));
        ps.setString(2, company);
        ps.setString(3, description);
        ps.setBigDecimal(4, amount);
        ps.setString(5, authorizedBy);

        ps.executeUpdate();
        conn.commit(); // committing the transaction if successful

        return 1;
      }
      catch (Exception ex)
      {
        conn.rollback(); // rolling back if an error occurs
        System.out.println("An error occurred while adding the expense, no changes were made: " + ex.getMessage());
        ex.getStackTrace();
        return 0;
      }
    }
    catch (Exception ex)
    {
      System.out.println("Database connection error: " + ex.getMessage());
      return 0;
    }
  }

  public static int editExpense(int id)
  {

    Scanner scanner = new Scanner(System.in);

    String fetchSql = "SELECT * FROM Expenses WHERE expense_id = ?";
    try (Connection conn = DB.connect();
         PreparedStatement fetchPs = conn.prepareStatement(fetchSql))
    {
      fetchPs.setInt(1, id);
      ResultSet rs = fetchPs.executeQuery();

      if (!rs.next())
      {
        System.out.println("No expense found with the given ID.");
        return 0;
      }

      // Display current values
      System.out.println("Current values:");
      System.out.println("Date: " + rs.getDate("Date"));
      System.out.println("Description: " + rs.getString("Description"));
      System.out.println("Amount: " + rs.getDouble("Amount"));
      System.out.println("Company: " + rs.getString("Company"));
      System.out.println("AuthorizedBy: " + rs.getString("AuthorizedBy"));

      // Prompt for updates
      System.out.println("\nWhat would you like to edit? (Enter field name: Date, Description, Amount, Company, AuthorizedBy)");
      String field = scanner.nextLine();

      String updateSql = null;
      switch (field.toLowerCase()) {
        case "date":
          System.out.println("Enter new Date (yyyy-mm-dd):");
          String date = scanner.nextLine();

          try
          {
            java.sql.Date newDate = java.sql.Date.valueOf(date);
            updateSql = "UPDATE expenses SET Date = ? WHERE expense_id = ?";
            try (PreparedStatement updatePs = conn.prepareStatement(updateSql))
            {
              updatePs.setDate(1, newDate);
              updatePs.setInt(2, id);
              updatePs.executeUpdate();
            }
            catch (IllegalArgumentException ex)
            {
              System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
            break;
          }
          catch (Exception ex)
          {
            System.out.println("Database connection error: " + ex.getMessage());
          }


        case "description":
          System.out.println("Enter new Description:");
          String description = scanner.nextLine();
          updateSql = "UPDATE EXPENSES SET Description = ? WHERE expense_id = ?";
          try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
            updatePs.setString(1, description);
            updatePs.setInt(2, id);
            updatePs.executeUpdate();
          }
          break;

        case "amount":
          System.out.println("Enter new Amount:");
          BigDecimal amount = scanner.nextBigDecimal();
          updateSql = "UPDATE Expenses SET Amount = ? WHERE expense_id = ?";
          try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
            updatePs.setBigDecimal(1, amount);
            updatePs.setInt(2, id);
            updatePs.executeUpdate();
          }
          break;

        case "company":
          System.out.println("Enter new Company:");
          String company = scanner.nextLine();
          updateSql = "UPDATE Expenses SET Company = ? WHERE expense_id = ?";
          try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
            updatePs.setString(1, company);
            updatePs.setInt(2, id);
            updatePs.executeUpdate();
          }
          break;

        case "authorizedby":
          System.out.println("Enter new AuthorizedBy:");
          String authorizedBy = scanner.nextLine();
          updateSql = "UPDATE Expenses SET AuthorizedBy = ? WHERE expense_id = ?";
          try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
            updatePs.setString(1, authorizedBy);
            updatePs.setInt(2, id);
            updatePs.executeUpdate();
          }
          break;

        default:
          System.out.println("Invalid field name.");
          return 0;
      }

//      System.out.println("Expense updated successfully.");
      return 1;
//    String sql = "UPDATE Expenses SET Date = ?, Description = ?, Amount = ?, Company = ?, AuthorizedBy = ? WHERE ID = ?";
//
//    try (Connection conn = DB.connect();
//        PreparedStatement ps = conn.prepareStatement(sql))
//    {
//
//      ps.setDate(1, (java.sql.Date) date);
//      ps.setString(2, description);
//      ps.setDouble(3, amount);
//      ps.setString(4, company);
//      ps.setString(5, authorizedBy);
//
//      ps.executeUpdate();
//
//      return 1;
    }
    catch (Exception ex)
    {
      ex.getStackTrace();
      System.out.println("An error occurred while editing the expense: " + ex.getMessage());
      return 0;
    }
  }

//  private double getTotalExpenses()
public static void getTotalExpenses()
  {

    String sql = "SELECT SUM(Amount) AS total_expenses FROM Expenses";

    try (Connection conn = DB.connect();
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql))
    {
      if (rs.next())
      {
        totalExpenses = rs.getBigDecimal("total_expenses");
      }
      else
      {
        System.out.println("No expenses found!");
      }

      System.out.println("Total expenses: " + totalExpenses);
    }
    catch (Exception ex)
    {
      ex.getStackTrace();
      System.out.println("An error occurred while getting the expenses: " + ex.getMessage());
    }
  }

}
