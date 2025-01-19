package org.yourcompany.yourproject;

import java.util.*;

public class Transaction 
{
  private Date Date;
  private String Company;
  private String Description;
  private float Amount;
  private String AuthorizedBy;


  public Transaction(Date date, String company, String description, float amount)
  {
    this.Date = date;
    this.Company = company;
    this.Description = description;
    this.Amount = amount;
    this.AuthorizedBy = "";
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

  public String getAuthorizedBy() {
    return AuthorizedBy;
  }

  public void setAuthorizedBy(String authorizedBy) {
    AuthorizedBy = authorizedBy;
  }
}