-- schema.sql

CREATE DATABASE IF NOT EXISTS easybankdb;

USE easybankdb;  -- Uncomment this line if running manually in a SQL client

-- User Table
CREATE TABLE IF NOT EXISTS user (
    userID BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    branch VARCHAR(255),
    address VARCHAR(255)
);

-- Account Table
CREATE TABLE IF NOT EXISTS account (
    accountID BIGINT AUTO_INCREMENT PRIMARY KEY,
    accountNumber VARCHAR(255) NOT NULL,
    accountType VARCHAR(50) NOT NULL,
    balance DECIMAL(15, 2) NOT NULL,
    userID BIGINT NOT NULL,
    FOREIGN KEY (userID) REFERENCES user(userID)
);

-- Transaction Table
CREATE TABLE IF NOT EXISTS transaction (
    transactionID BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(15, 2) NOT NULL,
    transferReference VARCHAR(255) NOT NULL,
    transferType VARCHAR(50) NOT NULL,
    date TIMESTAMP NOT NULL,
    fromAccount BIGINT NOT NULL,
    toAccount BIGINT NOT NULL,
    userID BIGINT NOT NULL,
    FOREIGN KEY (fromAccount) REFERENCES account(accountID),
    FOREIGN KEY (toAccount) REFERENCES account(accountID),
    FOREIGN KEY (userID) REFERENCES user(userID)
);

-- Budget Table
CREATE TABLE IF NOT EXISTS budget (
    budgetID BIGINT AUTO_INCREMENT PRIMARY KEY,
    budgetName VARCHAR(255) NOT NULL,
    budgetFrequency VARCHAR(255) NOT NULL,
    budgetLimit DECIMAL(15, 2) NOT NULL,
    spent DECIMAL(15, 2) NOT NULL,
    userID BIGINT NOT NULL,
    FOREIGN KEY (userID) REFERENCES user(userID)
);
