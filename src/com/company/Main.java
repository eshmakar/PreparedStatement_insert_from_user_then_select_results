package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/education?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "root";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into for_examples (name, age) values (upper(?), ?)");


            while (true) {
                System.out.println("Enter the name");
                String name = scanner.next();
                System.out.println("Enter the value of age");
                int age = scanner.nextInt();
                if (age != 0) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, age);
                    preparedStatement.execute();
                    System.out.println();
                } else {
                    break;
                }
            }


            ResultSet resultSet = preparedStatement.executeQuery("Select * from for_examples");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}