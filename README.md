# Smart Expense Splitter 💰

Smart Expense Splitter is a **Java-based expense management application** that allows users to manage groups, record expenses, and split costs efficiently.  
The project follows a **layered architecture** with clear separation between UI, business logic, data access, and database connectivity.

---

## 📌 Project Overview
Managing shared expenses among friends or groups can be complex.  
This application simplifies the process by:
- Storing users and groups
- Recording expenses
- Calculating shared costs
- Displaying expense data through a UI

---

## 🚀 Features
- User and group management
- Add and track expenses
- Database-backed expense storage
- Clean separation of layers (UI, DAO, Model, DB)
- Java Swing–based user interface

---

## 🛠 Tech Stack
- **Java (Java SE 21)**
- **Swing (UI)**
- **JDBC**
- **Object-Oriented Programming (OOP)**
- **Eclipse IDE**
- **Git & GitHub**

---

## 📂 Project Structure
SmartExpenseSplitter
├── src
│   ├── App.java
│   ├── dao
│   │   └── ExpenseDAO.java
│   ├── db
│   │   └── DBConnection.java
│   ├── model
│   │   ├── Expense.java
│   │   ├── Group.java
│   │   └── User.java
│   └── ui
│       ├── ExpenseTableModel.java
│       └── MainFrame.java
