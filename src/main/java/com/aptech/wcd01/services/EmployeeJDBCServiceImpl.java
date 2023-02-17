package com.aptech.wcd01.services;

import com.aptech.wcd01.models.Employee;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmployeeJDBCServiceImpl implements EmployeeJDBCService {
    String url = "jdbc:sqlserver://localhost:1433;databaseName=employee;encrypt=true;trustServerCertificate=true;";
    Connection connection;

    public EmployeeJDBCServiceImpl() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection(url, "sa", "1234");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addEmployee(Employee employee) {
        String query = "insert Employee(Id,Name,Address,Age)  values (?, ?, ? ,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setInt(4, employee.getAge());

            preparedStatement.execute();
            return true;

        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Employee employee) {

        String query = "update employee set Name = ? , Address = ?, Age = ? where Id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getAddress());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getId());
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            return false;

        }

    }

    @Override
    public boolean deleteEmployee(String id) {

        String query = "Delete from employee where Id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        String query = "Select * from employee";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Employee> employeeList = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getString("Id"));
                employee.setName(resultSet.getString("Name"));
                employee.setAddress(resultSet.getString("Address"));
                employee.setAge(resultSet.getInt("Age"));
                employeeList.add(employee);
            }
            return employeeList;
        } catch (SQLException ex) {
            return null;
        }

    }


    @Override
    public Employee getEmployeeById(String id) {

        String query = "Select * from employee where Id =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getString("Id"));
                employee.setName(resultSet.getString("Name"));
                employee.setAddress(resultSet.getString("Address"));
                employee.setAge(resultSet.getInt("Age"));
                return employee;

            }
        } catch (SQLException ex) {
            return null;

        }
        return null;
    }

    @Override
    public List<Employee> searchEmployeeByName(String searchStr) {

        String query = "Select * from employee where Name like ? or Address like ? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + searchStr + "%");
            preparedStatement.setString(2, "%" + searchStr + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Employee> employeeList = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getString("Id"));
                employee.setName(resultSet.getString("Name"));
                employee.setAddress(resultSet.getString("Address"));
                employee.setAge(resultSet.getInt("Age"));
                employeeList.add(employee);
            }
            return employeeList;
        } catch (SQLException ex) {
            return null;

        }
    }

}
