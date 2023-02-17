package com.aptech.wcd01.services;

import com.aptech.wcd01.models.Employee;

import java.util.List;

public interface EmployeeJPAService {

    public List<Employee> getAllEmployee();

    public List<Employee> searchEmployeeByName(String searchStr);

    public  Employee getEmployeeById(String id);

    public  boolean addEmployee(Employee employee);

    public boolean updateEmployee(Employee employee);

    public  boolean deleteEmployee(String id);
}
