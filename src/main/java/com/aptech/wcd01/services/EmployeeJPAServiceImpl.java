package com.aptech.wcd01.services;

import com.aptech.wcd01.models.Employee;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManager;
import jakarta.validation.*;

import java.util.List;
import java.util.Set;

public class EmployeeJPAServiceImpl implements EmployeeJPAService {

    private final EntityManager entiManager;

    public EmployeeJPAServiceImpl() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entiManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Employee> getAllEmployee() {

        return entiManager.createQuery("Select e from Employee e", Employee.class).getResultList();
    }

    @Override
    public Employee getEmployeeById(String id) {
        return entiManager.find(Employee.class, id);
    }

    @Override
    public boolean addEmployee(Employee employee) {
        try {
            String errMsg = validateEntity(employee);
            entiManager.getTransaction().begin();
            entiManager.persist(employee);
            entiManager.getTransaction().commit();

            return true;

        } catch (Exception ex) {

            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean updateEmployee(Employee employee) {
        entiManager.getTransaction().begin();
        entiManager.merge(employee);
        entiManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteEmployee(String id) {
        try{
            entiManager.getTransaction().begin();
            entiManager.remove(getEmployeeById(id));
            entiManager.getTransaction().commit();
            return true;
        }catch (Exception ex){
            return false;
        }

    }

    private String validateEntity(Employee employee) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        StringBuilder stringBuilder = new StringBuilder();

        for (ConstraintViolation<Employee> violation : violations) {
            stringBuilder.append(violation.getMessageTemplate()).append("\n").append(violation.getMessageTemplate()).append("\n");

        }
        return stringBuilder.toString();
    }
}
