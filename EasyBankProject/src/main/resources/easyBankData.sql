-- data.sql

-- Insert sample user 1
INSERT INTO users (username, email, password, name) VALUES
('johndoe', 'johndoe@example.com', 'password123', 'John Doe');

-- Insert sample accounts for user 1 with Savings and Current accounts
INSERT INTO accounts (accountNumber, accountType, balance, userID) VALUES
('100', 'Savings', 9950.0, 1);  -- Account 100: Current account with balance of 9950
INSERT INTO accounts (accountNumber, accountType, balance, userID) VALUES
('101', 'Current', 100.0, 1);   -- Account 101: Savings account with balance of 100

-- Insert a transaction: Transfer of 50 from account id 2 and account number 101 (Current) to account id 1 and account number 100 (Savings)
INSERT INTO transactions (amount, transferReference, transferType, date, fromAccount, toAccount, userID) VALUES
(50.0, 'Transfer from Account 101 to Account 100', 'Transfer', NOW(), 2, 1, 1);

-- Update Account 101 (decrease balance by 50)
UPDATE accounts
SET balance = balance - 50.0
WHERE accountNumber = '101';

-- Update Account 100 (increase balance by 50)
UPDATE accounts
SET balance = balance + 50.0
WHERE accountNumber = '100';

-- Insert a budget for groceries with a limit of 20 per week for user 1
INSERT INTO budgets (budgetName, budgetFrequency, budgetLimit, spent, userID) VALUES
('Groceries', 'Weekly', 20.0, 5.5, 1);

-- Insert sample user 2
INSERT INTO users (username, email, password, name) VALUES
('janedoe', 'janedoe@example.com', '321password', 'Jane Doe');

-- Insert accounts for user 2 with account types
INSERT INTO accounts (accountNumber, accountType, balance, userID) VALUES
('102', 'Savings', 11000.0, 2);  -- Account 102: Savings account with balance of 11000
