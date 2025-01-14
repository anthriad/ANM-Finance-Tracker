CREATE DATABASE anm_finance_tracker;

CREATE TABLE Expenses (
    Date VARCHAR(20),
    Company VARCHAR(255),
    Description VARCHAR(255),
    Amount MONEY,
    AuthorizedBy VARCHAR(255)
);

SELECT * FROM Expenses; -- returns Expenses table with 0 rows indicating successful creation

CREATE TABLE Deposits (
    Date VARCHAR(20),
    Company VARCHAR(255),
    Description VARCHAR(255),
    Amount MONEY
);

SELECT * FROM Deposits; -- returns Deposits table with 0 rows indicating successful creation

CREATE TABLE Net_Profit (
    Date VARCHAR(20),
    Net_Profit MONEY
);

SELECT * FROM Net_Profit; -- returns Net_Profit table with 0 rows indicating successful creation

CREATE TABLE Revenue (
    Date VARCHAR(20),
    Start_Of_Month MONEY,
    End_Of_Month MONEY
)

SELECT * FROM Revenue; -- returns Revenue table with 0 rows indicating successful creation
