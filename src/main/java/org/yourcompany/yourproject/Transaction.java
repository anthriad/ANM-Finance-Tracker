package org.yourcompany.yourproject;

import java.math.BigDecimal;
import java.util.*;

public class Transaction 
{
  private Date date;
  private String company;
  private String description;
  private BigDecimal amount;
  private String authorizedBy;


  public Transaction(Date date, String company, String description, BigDecimal amount)
  {
    this.date = date;
    this.company = company;
    this.description = description;
    this.amount = amount;
    this.authorizedBy = "";
  }

  public Date getDate()
  {
    return this.date;
  }

  public String getCompany()
  {
    return this.company;
  }
  
  public Date setDate(Date date)
  {
    this.date = date;
    return this.date;
  }

  public String setCompany(String company)
  {
    this.company = company;
    return this.company;
  }

  public String getDescription()
  {
    return this.description;
  }

  public String setDescription(String description)
  {
    this.description = description;
    return this.description;
  }

  public BigDecimal getAmount()
  {
    return this.amount;
  }

  public String getAuthorizedBy() {
    return this.authorizedBy;
  }

  public void setAuthorizedBy(String authorizedBy)
  {
    this.authorizedBy = authorizedBy;
  }
}