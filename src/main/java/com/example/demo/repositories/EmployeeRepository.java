package com.example.demo.repositories;

import com.example.demo.models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    //This method will find all employees
    public List<Employee> findAllEmployees(){
        List<Employee> allEmployees = new ArrayList<Employee>();

        try {

            //lavet et statement
            PreparedStatement ps = establishConnection().prepareStatement("SELECT * FROM emp");

            //eksekvere en query
            ResultSet rs = ps.executeQuery();

            //Bruge resultatet til noget
            while(rs.next()){
                Employee tmp = new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8)
                );
                allEmployees.add(tmp);
            }

        } catch (SQLException e) {
            return null;
        }
        return allEmployees;
    }

    public Employee findSingleEmployeeByEmpno(int empno){
        Employee employeeToReturn = null;

        try {
            PreparedStatement ps = establishConnection().prepareStatement("SELECT * FROM emp WHERE empno = ?");
            ps.setInt(1 , empno);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                employeeToReturn = new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8)
                );
            }


        }
        catch(SQLException e){
            return null;
        }
        return employeeToReturn;
    }

    public void createNewEmployee(Employee e){

    }

    private Connection establishConnection() throws SQLException {
        //Lav en forbindelse
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_company","dean","securePassword");

        return conn;
    }
}
