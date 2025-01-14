import java.util.*;

public class Transaction 
{
  private Date Date;
  private String Company;
  private String Description;
  private float Amount;


  public Transaction(Date date, String company, String description, float amount)
  {
    this.Date = date;
    this.Company = company;
    this.Description = description;
    this.Amount = amount;
  }

  public Date getDate()
  {
    return this.Date;
  }

  public String getCompany()
  {
    return this.Company;
  }
  
  public Date setDate(Date date)
  {
    this.Date = date;
    return this.Date;
  }

  public String setCompany(String company)
  {
    this.Company = company;
    return this.Company;
  }

  public String getDescription()
  {
    return this.Description;
  }

  public String setDescription(String description)
  {
    this.Description = description;
    return this.Description;
  }

  public float getAmount()
  {
    return this.Amount;
  }


}