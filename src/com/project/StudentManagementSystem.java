package com.project;

import java.sql.*;
import java.util.Scanner;

public class StudentManagementSystem {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String USER = "root";
    private static final String PASSWORD = "Vardhan@8053";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // 1️⃣ Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!");

            // 2️⃣ Connect to the database
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");

            // 3️⃣ Main menu loop
            while (true) {
                System.out.println("\n=== Student Management System ===");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                if (choice == 1) {
                    addStudent(con, sc);
                } else if (choice == 2) {
                    viewStudents(con);
                } else if (choice == 3) {
                    updateStudent(con, sc);
                } else if (choice == 4) {
                    deleteStudent(con, sc);
                } else if (choice == 5) {
                    System.out.println("Exiting... Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice! Try again.");
                }
            }

            // 4️⃣ Close resources
            con.close();
            sc.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database error!");
            e.printStackTrace();
        }
    }

    // ➤ Add Student
    private static void addStudent(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        System.out.print("Enter student age: ");
        int age = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter student grade: ");
        String grade = sc.nextLine();

        String sql = "INSERT INTO studentsdata (name, age, grade) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, grade);

        int rows = ps.executeUpdate();
        if (rows > 0) {
            System.out.println("✅ Student added successfully!");
        }

        ps.close();
    }

    // ➤ View All Students
    private static void viewStudents(Connection con) throws SQLException {
        String sql = "SELECT * FROM studentsdata";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        System.out.println("\nID\tName\tAge\tGrade");
        System.out.println("-----------------------------");

        while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" +
                               rs.getString("name") + "\t" +
                               rs.getInt("age") + "\t" +
                               rs.getString("grade"));
        }

        rs.close();
        st.close();
    }

    // ➤ Update Student
    private static void updateStudent(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter new name: ");
        String name = sc.nextLine();
        System.out.print("Enter new age: ");
        int age = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter new grade: ");
        String grade = sc.nextLine();

        String sql = "UPDATE studentsdata SET name=?, age=?, grade=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, grade);
        ps.setInt(4, id);

        int rows = ps.executeUpdate();
        if (rows > 0) {
            System.out.println("✅ Student updated successfully!");
        } else {
            System.out.println("❌ Student not found with ID: " + id);
        }

        ps.close();
    }

    // ➤ Delete Student
    private static void deleteStudent(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM studentsdata WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        int rows = ps.executeUpdate();
        if (rows > 0) {
            System.out.println("✅ Student deleted successfully!");
        } else {
            System.out.println("❌ Student not found with ID: " + id);
        }

        ps.close();
    }
}
