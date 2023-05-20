package com.pro.sky.DZ6_Kurs2.service;

import com.pro.sky.DZ6_Kurs2.exception.EmployeeAlreadyAddedException;
import com.pro.sky.DZ6_Kurs2.exception.EmployeeNotFoundException;
import com.pro.sky.DZ6_Kurs2.exception.EmployeeStorageIsFullException;
import com.pro.sky.DZ6_Kurs2.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    final static private int MAX_SIZE = 5;

    public Employee add(String firstName, String lastName) {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employeeToAdd = new Employee(firstName, lastName);
        if (employees.contains(employeeToAdd)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employeeToAdd);
        return employeeToAdd;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (!employees.contains(employeeToRemove)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employeeToRemove);

        return employeeToRemove;
    }

    public Employee find(String Name, String lastName) {
        for (Employee employee : employees) {
            if (Name.equalsIgnoreCase(employee.getFirstName()) & lastName.equalsIgnoreCase(employee.getLastName())) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public List<Employee> getAll() {
        return Collections.unmodifiableList(employees);
    }
}


