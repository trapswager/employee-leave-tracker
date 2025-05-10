# Employee Leave Tracker
**Student Name:** Adel Azhieva

## Project Description

The *Employee Leave Tracker* project is a console application written in Java. It is designed to track employee leave records. The program allows you to add, update, and delete employees, as well as generate reports and export/import data in CSV and JSON formats. All data is stored in files for persistence across sessions.

## Project Goals

- Create a console application for efficient management of employee leave records.
- Provide a simple and intuitive menu for users, allowing them to perform CRUD (Create, Read, Update, Delete) operations on employee records.
- Enable data export and import in popular formats (CSV and JSON).
- Store data in files and log user actions.
- Generate simple reports on the total number of employees and performed operations.

## Project Tasks

- Implement CRUD operations (Create, Read, Update, Delete) for employee records.
- Create and integrate a system for importing and exporting data in CSV and JSON formats.
- Ensure data is stored in files for persistence across sessions.
- Develop a simple console menu for user interaction.
- Log user actions in a text file for potential auditing.
- Create a report generation mechanism that shows general information about employees and operations.

## Used Classes and Data Structures

### `Employee.java`
This class represents an employee and contains the following fields:
- `id` — the unique identifier of the employee.
- `name` — the employee's name.
- `email` — the employee's email.
- `leaveDays` — the number of leave days.

The `toString()` method is used to display employee information.

### `EmployeeManager.java`
This class manages the list of employees and provides methods for adding, deleting, and updating employee records. It uses a `List<Employee>` collection to store employees.

Methods:
- `addEmployee(Employee e)` — adds a new employee to the list.
- `updateEmployee(int id, String name, String email, int leaveDays)` — updates employee information by ID.
- `deleteEmployee(int id)` — deletes an employee by ID.
- `getAllEmployees()` — returns a list of all employees.

### `FileHandler.java`
This class handles file operations. It is responsible for reading and writing employee data to TXT, CSV, and JSON files.

Methods:
- `loadEmployeesFromFile(String filename)` — loads employee data from a file.
- `saveEmployeesToFile(String filename, List<Employee> employees)` — saves employee data to a file.

### `ReportGenerator.java`
This class generates reports on the system's status, including the number of employees and total operations performed.

Methods:
- `generateReport(List<Employee> employees)` — generates a report with the current number of employees and performed operations.
- `log(String action)` — logs user actions in a log file for auditing purposes.

## Algorithms and Key Features

### CRUD Operations
The application implements standard CRUD operations for employees. Each operation validates data before making changes (e.g., checking for unique IDs when adding an employee or validating email format).

- **Add Employee Algorithm**: The user is prompted to enter a unique ID, name, email, and number of leave days. If the data is valid, the employee is added to the list.

### Data Import and Export
The system uses file handling to import and export data in CSV and JSON formats.

- **Data Import Algorithm**: The file is read and parsed, creating new employee objects that are added to the list.
- **Data Export Algorithm**: The program iterates through the list of employees and writes their data to a CSV or JSON file.

### Logging and Reporting
Every change in employee data (add, update, delete) is logged in the `log.txt` file with a timestamp.

- **Report Generation**: The program generates a report upon user request, which includes:
  - The total number of employees.
  - The total number of operations performed (e.g., adding, updating, deleting employees).

## Issues and Solutions

### Data Validation Issue
During development, I encountered a problem with data validation (e.g., email format and unique IDs). To solve this, I implemented loops with checks that require the user to input valid data.

- Email validation is handled using regular expressions to ensure the correct format.

### Data Persistence Issue
Another challenge was ensuring data persistence between sessions. This was solved by using methods to save and load data from files, allowing employee data to persist between program runs.

### File Handling Errors
There were input-output errors while working with files (for import/export). These were resolved using try-catch blocks to handle errors properly.

### Report Generation
To enhance functionality, I added the ability to generate reports showing the total number of employees and the total number of operations performed. This provides users with clear insights into the system’s state.

## Conclusion
The *Employee Leave Tracker* project successfully meets its goals. It allows for effective management of employee data, data export/import, and report generation. The implemented logging and file storage ensure that data is available between sessions, and the simple console interface makes the application user-friendly.

