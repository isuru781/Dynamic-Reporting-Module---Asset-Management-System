-- Insert sample data into Assets Table
INSERT INTO assets (asset_name, asset_type, purchase_date, purchase_cost, current_value, status) VALUES
('Laptop Dell XPS 13', 'Computer', '2023-01-15', 1200.00, 950.00, 'Active'),
('Office Desk', 'Furniture', '2022-06-20', 300.00, 250.00, 'Active'),
('Printer HP LaserJet', 'Equipment', '2023-03-10', 800.00, 700.00, 'Active'),
('Conference Room Table', 'Furniture', '2022-11-05', 1500.00, 1350.00, 'Active'),
('Server Dell PowerEdge', 'Computer', '2021-09-12', 5000.00, 4000.00, 'Active');

-- Insert sample data into Categories Table
INSERT INTO categories (category_name, description) VALUES
('IT Equipment', 'All IT and computing equipment'),
('Furniture', 'Office furniture and fixtures'),
('Machinery', 'Industrial machinery and equipment'),
('Vehicles', 'Company vehicles'),
('Office Supplies', 'General office supplies');

-- Insert sample data into Employees Table
INSERT INTO employees (employee_name, email, phone, department, hire_date) VALUES
('John Smith', 'john.smith@company.com', '555-0101', 'IT', '2022-01-10'),
('Sarah Johnson', 'sarah.johnson@company.com', '555-0102', 'Finance', '2021-05-15'),
('Michael Chen', 'michael.chen@company.com', '555-0103', 'Operations', '2023-02-20'),
('Emily Davis', 'emily.davis@company.com', '555-0104', 'HR', '2022-08-12'),
('Robert Wilson', 'robert.wilson@company.com', '555-0105', 'Management', '2020-03-08');

-- Insert sample data into Locations Table
INSERT INTO locations (location_name, city, state, country, postal_code) VALUES
('Headquarters', 'New York', 'NY', 'USA', '10001'),
('Branch Office', 'Los Angeles', 'CA', 'USA', '90001'),
('Warehouse', 'Chicago', 'IL', 'USA', '60601'),
('Regional Office', 'Houston', 'TX', 'USA', '77001'),
('Distribution Center', 'Miami', 'FL', 'USA', '33101');

-- Insert sample data into Maintenance Table
INSERT INTO maintenance (asset_id, maintenance_date, maintenance_type, cost, performed_by, notes) VALUES
(1, '2024-01-10', 'Software Update', 50.00, 'John Smith', 'Updated Windows and drivers'),
(2, '2024-02-15', 'Cleaning', 30.00, 'Emily Davis', 'Deep cleaning and polishing'),
(3, '2024-03-05', 'Toner Replacement', 80.00, 'John Smith', 'Replaced toner cartridges'),
(4, '2024-04-12', 'Repair', 150.00, 'Michael Chen', 'Fixed broken leg'),
(5, '2024-05-20', 'Hardware Upgrade', 500.00, 'John Smith', 'Added RAM and SSD');

