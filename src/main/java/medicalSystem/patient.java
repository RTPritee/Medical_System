package medicalSystem;

import java.sql.*;
import java.util.*;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Collections;
//import java.util.Scanner;

public class patient {
    private Connection connection;
    private Scanner scanner;
    public patient(Connection connection,Scanner scanner){
        this.connection = connection;
        this.scanner =  scanner;
    }
    public void addPatient(){
        System.out.println("Enter patient name:");
        String name = scanner.next();
        System.out.println("Enter patient age:");
        int age = scanner.nextInt();
        System.out.println("Enter patient gender:");
        String gender = scanner.next();

        try {
        String query = "INSERT INTO `patients`(`p_name`,`p_age`,`p_gender`)VALUES(?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows > 0){
                System.out.println("Patient added successfully");
            }else System.out.println("failed to add");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewPatients(){
        String query = "select * from `patients`;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+------------+--------------------+----------+------------+");
            System.out.println("| Patient Id | Name               | Age      | Gender     |");
            System.out.println("+------------+--------------------+----------+------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("p_id");
                String name = resultSet.getString("p_name");
                int age = resultSet.getInt("p_age");
                String gender = resultSet.getString("p_gender");
                System.out.printf("| %-10s | %-18s | %-8s | %-10s |\n", id, name, age, gender);
                System.out.println("+------------+--------------------+----------+------------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
