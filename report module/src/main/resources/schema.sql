-- Create Database
CREATE DATABASE IF NOT EXISTS asset_management;
USE asset_management;

-- Create Assets Table
CREATE TABLE IF NOT EXISTS assets (
    asset_id INT PRIMARY KEY AUTO_INCREMENT,
    asset_name VARCHAR(255) NOT NULL,
    asset_type VARCHAR(100) NOT NULL,
    purchase_date DATE,
    purchase_cost DECIMAL(10, 2),
    current_value DECIMAL(10, 2),
    status VARCHAR(50)
);

-- Create Categories Table
CREATE TABLE IF NOT EXISTS categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(100) NOT NULL,
    description TEXT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Employees Table
CREATE TABLE IF NOT EXISTS employees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_name VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    department VARCHAR(100),
    hire_date DATE
);

-- Create Locations Table
CREATE TABLE IF NOT EXISTS locations (
    location_id INT PRIMARY KEY AUTO_INCREMENT,
    location_name VARCHAR(100) NOT NULL,
    city VARCHAR(100),
    state VARCHAR(50),
    country VARCHAR(100),
    postal_code VARCHAR(20)
);

-- Create Maintenance Table
CREATE TABLE IF NOT EXISTS maintenance (
    maintenance_id INT PRIMARY KEY AUTO_INCREMENT,
    asset_id INT,
    maintenance_date DATE,
    maintenance_type VARCHAR(100),
    cost DECIMAL(10, 2),
    performed_by VARCHAR(255),
    notes TEXT
);

