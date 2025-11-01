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

