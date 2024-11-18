-- schema.sql

CREATE DATABASE IF NOT EXISTS easybankdb;

USE easybankdb;


-- User Table
CREATE TABLE IF NOT EXISTS users (
    userID BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    branch VARCHAR(255),
    address VARCHAR(255)
);

-- Account Table
CREATE TABLE IF NOT EXISTS accounts (
    accountID BIGINT AUTO_INCREMENT PRIMARY KEY,
    accountNumber VARCHAR(255) NOT NULL,
    accountType VARCHAR(50) NOT NULL,
    balance DECIMAL(15, 2) NOT NULL,
    userID BIGINT NOT NULL,
    FOREIGN KEY (userID) REFERENCES users(userID)
);

-- Transaction Table
CREATE TABLE IF NOT EXISTS transactions (
    transactionID BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(15, 2) NOT NULL,
    transferReference VARCHAR(255) NOT NULL,
    transferType VARCHAR(50) NOT NULL,
    date TIMESTAMP NOT NULL,
    fromAccount BIGINT NOT NULL,
    toAccount BIGINT NOT NULL,
    userID BIGINT NOT NULL,
    FOREIGN KEY (fromAccount) REFERENCES accounts(accountID),
    FOREIGN KEY (toAccount) REFERENCES accounts(accountID),
    FOREIGN KEY (userID) REFERENCES users(userID)
);

-- Budget Table
CREATE TABLE IF NOT EXISTS budgets (
    budgetID BIGINT AUTO_INCREMENT PRIMARY KEY,
    budgetName VARCHAR(255) NOT NULL,
    budgetFrequency VARCHAR(255) NOT NULL,
    budgetLimit DECIMAL(15, 2) NOT NULL,
    spent DECIMAL(15, 2) NOT NULL,
    userID BIGINT NOT NULL,
    FOREIGN KEY (userID) REFERENCES users(userID)
);
