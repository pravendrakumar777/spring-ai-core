package com.niiran.engineering.service;

import com.niiran.engineering.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    void deleteEmployee(Long id);
    List<Employee> getEmployeesByDepartment(String department);
    List<Employee> getEmployeesByDesignation(String designation);
    List<Employee> getActiveEmployees();
    long countEmployees();
    long countEmployeesByDepartment(String department);

}
