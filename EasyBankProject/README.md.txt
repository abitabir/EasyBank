# EasyBank: A Simple Finance Tool for Everyone

## Table of Contents
- [Introduction](#introduction)
- [Core Features](#core-features)
- [Project Architecture](#project-architecture)
- [Setup and Installation](#setup-and-installation)
- [Testing](#testing)
- [Documentation and Planning](#documentation-and-planning)
- [Presentation](#presentation)
- [Contributing](#contributing)
- [License](#license)

## Introduction
**EasyBank** is a personal finance management system that helps users manage income, expenses, and budgets. It features a language toggle for accessibility, allowing users to switch between "Simple" and "Technical" financial terms.

## Core Features
### User Authentication (JWT)
- **Backend**: User registration and login secured with Spring Security and JWT.
- **Frontend**: JSP templates for registration and login.
- **Testing**: Authentication tested with Postman.

### CRUD for Transactions
- **Backend**: API endpoints for creating, reading, updating, and deleting transactions.
- **Frontend**: JSP pages for transaction management.
- **Testing**: CRUD operations tested via Postman.

### Budget Management
- **Backend**: CRUD endpoints for budget management.
- **Database**: `Budgets` table with fields for category, limit, and spent.
- **Frontend**: JSP pages to manage budget categories.
- **Testing**: CRUD validated using Postman.

### Financial Insights
- **Backend**: API provides insights like total income and expenses.
- **Frontend**: Dashboard to display financial data.

### Language Toggle
- Switch between "Simple" and "Technical" financial terms using properties files.

### Security
- Spring Security and JWT for authentication.
- Input validation to protect against SQL injection and XSS.

## Project Architecture
### Backend
- **Framework**: Spring Boot
- **Database**: MySQL with Hibernate (JPA).
- **Security**: JWT-based authentication.

### Frontend (Optional)
- **Framework**: JSP (Java Server Pages).

### Database
- **Type**: MySQL
- **Schema**: Includes Users, Transactions, and Budgets tables.
- **Setup Files**:
  - `easyBankSchema.sql` for database structure.
  - `easyBankData.sql` for initial data.

## Setup and Installation
### Prerequisites
- Java 11+
- Apache Maven
- MySQL Server
- Postman

### Database Setup
1. Create the database:
   ```bash
   mysql -u root -p -e "CREATE DATABASE easybankdb;"

## Run the Schema and Data Scripts

Run the following commands to set up the database:

    ```bash
    mysql -u root -p easybankdb < path/to/easyBankSchema.sql
    mysql -u root -p easybankdb < path/to/easyBankData.sql

## Project Setup

### Clone the Repository

Clone the project repository:

    ```bash
    git clone https://github.com/yourusername/EasyBank.git


## Build the Project

Navigate to the project directory and build the project:

    ```bash
    cd EasyBank
    mvn clean install
    mvn spring-boot:run

Access the application at http://localhost:8080/easybank.

## Testing

- Use **Postman** to test API endpoints for user authentication, transactions, and budget management.
- JUnit test cases are located in `src/test/java`.

## Documentation and Planning

- **UML Diagrams**: Created with StarUML.
- **ERD**: For MySQL database.
- **Flowchart**: Visualizing user flow.

## License

Licensed under the MIT License.
