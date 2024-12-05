package medicalSystem;
import java.util.*;
import java.sql.*;
public class medicalSystem {
    public static final String url = "jdbc:mysql://localhost:3306/localhost";

    public static final String username = "root";
    public static final String password = "pritee123";

    public static void main(String[] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            patient patient = new patient(connection,scanner);
            System.out.println("ADD PATIENT");
            patient.addPatient();
            System.out.println("VIEW PATIENT");
            patient.viewPatients();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
