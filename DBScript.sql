-- Create the database
CREATE DATABASE IF NOT EXISTS balancee_rewards;

-- Use the created database
USE balancee_rewards;

-- Create customers table
CREATE TABLE IF NOT EXISTS customers (
    customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    total_cashback DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    current_balance DECIMAL(10, 2) NOT NULL DEFAULT 0.00
    );

-- Create cashback_transactions table
CREATE TABLE IF NOT EXISTS cashback_transactions (
                                                     transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                     customer_id BIGINT NOT NULL,
                                                     transaction_date DATETIME NOT NULL,
                                                     amount_earned DECIMAL(10, 2) NOT NULL,
    description VARCHAR(255),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
    );

-- Disable foreign key checks to avoid issues with order of insertion
SET FOREIGN_KEY_CHECKS = 0;

-- Clear existing data
TRUNCATE TABLE customers;
TRUNCATE TABLE cashback_transactions;

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;

-- Insert sample customers
INSERT INTO customers (customer_id, customer_name, total_cashback, current_balance)
VALUES
    (1, 'John Doe', 250.00, 125.00),
    (2, 'Jane Smith', 300.00, 150.00),
    (3, 'James Smith', 300.00, 150.00);

-- Insert sample cashback transactions for John Doe
INSERT INTO cashback_transactions (customer_id, transaction_date, amount_earned, description)
VALUES
    (1, NOW() - INTERVAL 30 DAY, 50.00, 'Nike Air Max purchase'),
    (1, NOW() - INTERVAL 25 DAY, 30.00, 'Nike running shorts'),
    (1, NOW() - INTERVAL 20 DAY, 75.00, 'Nike custom shoes'),
    (1, NOW() - INTERVAL 15 DAY, 45.00, 'Nike fitness tracker'),
    (1, NOW() - INTERVAL 10 DAY, 50.00, 'Nike gift card purchase');

-- Insert sample cashback transactions for Jane Smith
INSERT INTO cashback_transactions (customer_id, transaction_date, amount_earned, description)
VALUES
    (2, NOW() - INTERVAL 28 DAY, 60.00, 'Nike Zoom purchase'),
    (2, NOW() - INTERVAL 21 DAY, 40.00, 'Nike sports bra'),
    (2, NOW() - INTERVAL 14 DAY, 80.00, 'Nike training shoes'),
    (2, NOW() - INTERVAL 7 DAY, 70.00, 'Nike smartwatch'),
    (2, NOW() - INTERVAL 1 DAY, 50.00, 'Nike online course');
