import java.util.*;
import java.util.Date;


public class Expenses extends Transaction
{
	  private int totalExpenses = 0;
  // public Expenses(Date date, String description, float amount)
	// {
	// 	super(date, "Expenses", description, amount);
	// }

  // primary constructor
	public Expenses(Date date, String description, float amount, String company)
	{
		super(date, company, description, amount);
	}

  // overloaded constructor
  public Expenses(float amount, String description) 
  {
    this(new Date(), description, amount, "");
  }

  private void displayAllExpenses()
  {
    try 
    {
      
    }
    catch (Exception ex)
    {

    }
  }

  private void deleteExpense()
  {
    try 
    {

    }
    catch (Exception ex)
    {
      
    }
  }

  private void addExpense()
  {
    try 
    {

    }
    catch (Exception ex)
    {
      
    }
  }

  private void editExpense() 
  {
    try 
    {

    }
    catch (Exception ex)
    {
      
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

  private int getTotalExpenses()
  {
    try 
    {
      return totalExpenses; // returning the total amount of expenses that have accrued
    }
    catch (Exception ex)
    {
      return 0;
    }
  }

}
