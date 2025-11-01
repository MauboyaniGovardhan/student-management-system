# 🎓 Student Management System

A simple **Java console-based application** to manage student records using **JDBC** and **MySQL**.

## 🚀 Features
- Add new student
- View all students
- Update existing student details
- Delete student by ID

## 🧠 Technologies Used
- Java
- JDBC (Java Database Connectivity)
- MySQL

## 🗄️ Database Setup
```sql
CREATE DATABASE studentdb;
USE studentdb;

CREATE TABLE studentsdata (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  age INT,
  grade VARCHAR(10)
);


## ▶️ How to Run
1. Import project in Eclipse or any Java IDE.
2. Make sure MySQL server is running and database is created.
3. Update the database credentials (`URL`, `USER`, `PASSWORD`) in the Java program.
4. Run `StudentManagementSystem.java` from your IDE.
