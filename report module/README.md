# 📊 Dynamic Reporting Module - Asset Management System



> A full-stack application that allows users to execute custom SQL queries and generate PDF reports with automatic table formatting. Built with Spring Boot 3, MySQL, iText 7, and a modern HTML/JavaScript frontend.

---

## 📋 Table of Contents
- [Features](#features)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Security](#security)
- [Database Schema](#database-schema)
- [Usage Examples](#usage-examples)
- [Testing](#testing)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Features

✅ **Dynamic SQL Query Execution** - Execute any SELECT query against the database
✅ **Automatic PDF Generation** - Convert query results into formatted PDF reports with tables
✅ **Security Layer** - SQL injection prevention with keyword blocking
✅ **Responsive UI** - Modern Vue.js 3 interface with Composition API
✅ **Sample Data** - 5 pre-built tables with 5 rows of sample data each
✅ **Error Handling** - Comprehensive exception handling and validation

## 🏗️ Architecture

### Backend Stack
- **Framework**: Spring Boot 3.x
- **Language**: Java 21
- **Database**: MySQL 8.0+
- **PDF Library**: iText 7.2.5
- **Data Access**: Spring Data JDBC (JdbcTemplate)
- **Web Server**: Embedded Tomcat

### Frontend Stack
- **Type**: Pure HTML5 + Vanilla JavaScript
- **HTTP Client**: Fetch API
- **Styling**: Modern CSS3 with responsive design
- **Features**: No build tools required, works standalone

## 📁 Project Structure

```
report-module/
├── src/
│   ├── main/
│   │   ├── java/lk/isuru781/reportmodule/
│   │   │   ├── ReportModuleApplication.java       # Main app entry point
│   │   │   ├── config/
│   │   │   │   └── WebConfig.java                 # CORS & Web configuration
│   │   │   ├── controller/
│   │   │   │   ├── ReportController.java          # REST API endpoints (POST /api/reports/download)
│   │   │   │   └── TestController.java            # Test endpoints
│   │   │   ├── dto/
│   │   │   │   └── ReportRequest.java             # Request DTO
│   │   │   ├── service/
│   │   │   │   └── ReportService.java             # Business logic & PDF generation
│   │   │   └── util/
│   │   │       └── QueryValidator.java            # SQL validation & security
│   │   └── resources/
│   │       ├── application.properties              # Database configuration
│   │       ├── schema.sql                          # Database schema (5 tables)
│   │       ├── data.sql                            # Sample data (5 rows per table)
│   │       └── ReportGenerator.html                # Frontend UI (Pure HTML + JS)
│   └── test/
│       └── java/lk/isuru781/reportmodule/
│           └── ReportModuleApplicationTests.java   # Integration tests
├── pom.xml                                         # Maven dependencies
├── mvnw & mvnw.cmd                                # Maven wrapper scripts
├── README.md                                       # This file
├── QUICK_START.md                                 # Quick start guide
├── PROJECT_COMPLETION.md                          # Project completion checklist
└── TESTING.md                                     # Detailed testing guide
```

## 🚀 Getting Started

### Prerequisites
- **Java**: 21 or higher
- **Maven**: 3.8.1 or higher
- **MySQL**: 8.0 or higher
- **Browser**: Any modern browser (Chrome, Firefox, Safari, Edge)
- **Text Editor/IDE**: IntelliJ IDEA, VS Code, Eclipse, or similar

### Backend Setup (Spring Boot)

#### Step 1: Clone the Repository
```bash
git clone https://github.com/yourusername/Dynamic-Reporting-Module.git
cd "Dynamic-Reporting-Module"
```

#### Step 2: Configure Database Connection
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/asset_management
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

#### Step 3: Initialize Database
```bash
# Create the database and tables
mysql -u root -p < src/main/resources/schema.sql

# Insert sample data
mysql -u root -p < src/main/resources/data.sql
```

Or run these scripts manually through MySQL Workbench.

#### Step 4: Build and Run
```bash
# Using Maven wrapper (recommended)
./mvnw clean install
./mvnw spring-boot:run

# Or with installed Maven
mvn clean install
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### Frontend Setup (HTML + JavaScript)

The frontend is a **pure HTML file** with no build tools required!

#### Option 1: Serve from Spring Boot (Recommended)
The `ReportGenerator.html` is automatically served at:
```
http://localhost:8080/ReportGenerator.html
```

#### Option 2: Open Directly in Browser
1. Navigate to `src/main/resources/ReportGenerator.html`
2. Right-click → "Open with Browser"
3. Or drag the file into your browser

#### Option 3: Use a Simple HTTP Server
```bash
# From the src/main/resources directory
python -m http.server 8000
# Access at http://localhost:8000/ReportGenerator.html
```

### Verify Installation

1. **Check Backend Health**:
   ```bash
   curl http://localhost:8080/api/reports/health
   ```
   Expected response: `Report Module API is running!`

2. **Open Frontend**:
   Visit `http://localhost:8080/ReportGenerator.html`

3. **Test with Sample Query**:
   ```sql
   SELECT * FROM assets LIMIT 5
   ```
   Click "Generate PDF" and verify download

## 📡 API Documentation

### Base URL
```
http://localhost:8080/api/reports
```

### Endpoints

#### 1. Generate PDF Report
```http
POST /api/reports/download
Content-Type: application/json
```

**Request Body**:
```json
{
  "query": "SELECT * FROM assets WHERE status = 'Active'"
}
```

**Success Response** (200):
- **Content-Type**: `application/pdf`
- **Body**: Binary PDF file
- **Headers**:
  ```
  Content-Disposition: attachment; filename="report.pdf"
  Content-Length: <file-size>
  ```

**Error Response** (400):
```
Plain text error message
Example: "Invalid query. Only SELECT statements are allowed."
```

**Error Response** (500):
```
Error generating report: <error-details>
```

#### 2. Health Check
```http
GET /api/reports/health
```

**Response** (200):
```
Report Module API is running!
```

### cURL Examples

**Generate PDF**:
```bash
curl -X POST http://localhost:8080/api/reports/download \
  -H "Content-Type: application/json" \
  -d '{"query":"SELECT * FROM assets"}' \
  --output report.pdf
```

**Health Check**:
```bash
curl http://localhost:8080/api/reports/health
```

### JavaScript/Fetch Examples

**Generate PDF with Download**:
```javascript
async function generatePDF(query) {
  try {
    const response = await fetch('http://localhost:8080/api/reports/download', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ query })
    });

    if (!response.ok) {
      const error = await response.text();
      throw new Error(error);
    }

    const blob = await response.blob();
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'report.pdf';
    a.click();
    window.URL.revokeObjectURL(url);
  } catch (error) {
    console.error('Error:', error);
  }
}
```

**Health Check**:
```javascript
async function checkHealth() {
  try {
    const response = await fetch('http://localhost:8080/api/reports/health');
    const message = await response.text();
    console.log(message);
  } catch (error) {
    console.error('Error:', error);
  }
}
```

## 🔒 Security Features

### SQL Validation
- ✅ **Only SELECT statements** are allowed
- ✅ **Dangerous keywords blocked**: DROP, DELETE, UPDATE, TRUNCATE, INSERT, ALTER, EXEC, EXECUTE, SCRIPT, UNION, --, /*, */
- ✅ **Empty query validation**
- ✅ **Case-insensitive validation**

### Implementation
The `QueryValidator` utility class validates all incoming queries before execution:
```java
if (!QueryValidator.isValidSelectQuery(query)) {
    return ResponseEntity.badRequest()
            .body("Invalid query. Only SELECT statements are allowed.");
}
```

## 📊 Database Schema

### Tables (5 Pre-built)

#### 1. **assets**
```sql
asset_id (INT, PK)
asset_name (VARCHAR)
asset_type (VARCHAR)
purchase_date (DATE)
purchase_cost (DECIMAL)
current_value (DECIMAL)
status (VARCHAR)
```

#### 2. **categories**
```sql
category_id (INT, PK)
category_name (VARCHAR)
description (TEXT)
created_date (TIMESTAMP)
```

#### 3. **employees**
```sql
employee_id (INT, PK)
employee_name (VARCHAR)
email (VARCHAR)
phone (VARCHAR)
department (VARCHAR)
hire_date (DATE)
```

#### 4. **locations**
```sql
location_id (INT, PK)
location_name (VARCHAR)
city (VARCHAR)
state (VARCHAR)
country (VARCHAR)
postal_code (VARCHAR)
```

#### 5. **maintenance**
```sql
maintenance_id (INT, PK)
asset_id (INT)
maintenance_date (DATE)
maintenance_type (VARCHAR)
cost (DECIMAL)
performed_by (VARCHAR)
notes (TEXT)
```

## 📋 Sample Queries

Try these queries in the UI:

### 1. Get All Active Assets
```sql
SELECT * FROM assets WHERE status = 'Active'
```

### 2. Employee Directory
```sql
SELECT employee_name, email, department FROM employees ORDER BY department
```

### 3. All Locations
```sql
SELECT location_name, city, state FROM locations
```

### 4. Expensive Maintenance Records
```sql
SELECT * FROM maintenance WHERE cost > 100
```

### 5. Asset Categories
```sql
SELECT category_name, description FROM categories
```

## 📦 Dependencies

### Maven Dependencies (pom.xml)
```xml
<!-- Spring Boot 3.x -->
spring-boot-starter
spring-boot-starter-web
spring-boot-starter-jdbc

<!-- MySQL Driver -->
mysql-connector-j

<!-- PDF Library -->
itext7-core (v7.2.5)

<!-- Testing -->
spring-boot-starter-test
```

### Frontend Dependencies
- **None!** Pure HTML5 + Vanilla JavaScript
- Uses browser's built-in **Fetch API** for HTTP requests
- No npm, no Node.js, no build tools required

### System Requirements
| Component | Minimum | Recommended |
|-----------|---------|-------------|
| Java | 21 | 21+ |
| Maven | 3.8.1 | 3.9.0+ |
| MySQL | 8.0 | 8.0+ |
| Browser | ES6 support | Chrome 90+, Firefox 88+, Safari 14+, Edge 90+ |
| RAM | 2GB | 4GB+ |
| Disk Space | 500MB | 1GB+ |

## 🎨 UI Features

- **Modern Gradient Background** - Purple to violet gradient
- **Responsive Design** - Works on desktop, tablet, and mobile
- **Sample Query Buttons** - Click to load pre-built queries
- **Error/Success Messages** - Clear feedback on operations
- **Loading State** - Disabled button while processing
- **Auto-download** - PDF automatically downloads to your device
- **Syntax Highlighting** - Code blocks for sample queries

## 🧪 Testing the Application

### Manual Test Cases

1. **Test Valid SELECT Query**
   - Query: `SELECT * FROM assets`
   - Expected: PDF downloads successfully with all assets

2. **Test with WHERE Clause**
   - Query: `SELECT * FROM assets WHERE status = 'Active'`
   - Expected: PDF contains only active assets

3. **Test SQL Injection Prevention**
   - Query: `SELECT * FROM assets; DROP TABLE assets;`
   - Expected: Error message "Invalid query"

4. **Test Empty Query**
   - Query: (empty)
   - Expected: Error message "Query cannot be empty"

5. **Test Different Data Types**
   - Query: `SELECT * FROM maintenance WHERE cost > 100`
   - Expected: PDF with proper formatting for decimal values

## 🐛 Troubleshooting

### Issue: Connection refused on localhost:3306
**Solution**: Ensure MySQL is running
```bash
# Windows
net start MySQL80

# macOS
brew services start mysql-server

# Linux
sudo systemctl start mysql
```

### Issue: Database not found
**Solution**: Run `schema.sql` first to create the database and tables

### Issue: CORS errors in frontend
**Solution**: WebConfig.java is already configured for CORS. Ensure it's being loaded.

### Issue: PDF won't download
**Solution**: Check browser console for errors. Ensure Content-Type header is set correctly.

## 📈 Future Enhancements

- [ ] Add user authentication and authorization
- [ ] Implement query result pagination
- [ ] Add advanced PDF styling options (colors, fonts)
- [ ] Query history and saved queries
- [ ] Data export to CSV, Excel formats
- [ ] Chart generation from query results
- [ ] Query performance metrics
- [ ] Database connection pooling optimization

## 📚 Code Standards

- **Java Naming**: camelCase for methods/variables, PascalCase for classes
- **Comments**: JavaDoc for public methods
- **Exception Handling**: Try-catch with meaningful error messages
- **Code Organization**: Separation of concerns (Controller → Service → Repository)
- **Vue.js**: Composition API with setup syntax
- **CSS**: Scoped styling to prevent conflicts

## 🔗 API Request/Response Examples

### cURL Example
```bash
curl -X POST http://localhost:8080/api/reports/download \
  -H "Content-Type: application/json" \
  -d '{"query":"SELECT * FROM assets"}' \
  -o report.pdf
```

### JavaScript/Axios Example
```javascript
const response = await axios.post(
  'http://localhost:8080/api/reports/download',
  { query: 'SELECT * FROM assets' },
  { responseType: 'blob' }
);

const blob = new Blob([response.data], { type: 'application/pdf' });
const url = window.URL.createObjectURL(blob);
const link = document.createElement('a');
link.href = url;
link.download = 'report.pdf';
link.click();
```

## 📞 Support

For issues or questions:
1. Check the troubleshooting section above
2. Review the code comments in each file
3. Check MySQL and Spring Boot logs for detailed errors

## 📄 License

This project is part of a learning exercise for Spring Boot.

---

**Happy Reporting! 📊✨**

