package com.niiran.engineering.tools;

import com.niiran.engineering.entity.Employee;
import com.niiran.engineering.service.EmployeeService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeTool {

    private final EmployeeService employeeService;

    public EmployeeTool(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Tool(description = "Create a new employee")
    public Employee createEmployee(String employeeName, String email, String phone, String department, String designation, Double salary, String address) {
        Employee employee = new Employee();
        employee.setEmployeeName(employeeName);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setDepartment(department);
        employee.setDesignation(designation);
        employee.setSalary(salary);
        employee.setAddress(address);
        employee.setActive(true);
        return employeeService.createEmployee(employee);
    }

    @Tool(description = "Update an existing employee")
    public Employee updateEmployee(Long id, String employeeName, String email, String phone, String department, String designation, Double salary, String address, Boolean active) {
        Employee employee = new Employee();
        employee.setEmployeeName(employeeName);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setDepartment(department);
        employee.setDesignation(designation);
        employee.setSalary(salary);
        employee.setAddress(address);
        employee.setActive(active);
        return employeeService.updateEmployee(id, employee);
    }

    @Tool(description = "Get employee by id")
    public Employee getEmployee(Long id) {
        return employeeService.getEmployeeById(id);
    }

    @Tool(description = "Get all employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Tool(description = "Delete employee by id")
    public String deleteEmployee(Long id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully.";
    }

    @Tool(description = "Find employees by department")
    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeService.getEmployeesByDepartment(department);
    }

    @Tool(description = "Find employees by designation")
    public List<Employee> getEmployeesByDesignation(String designation) {
        return employeeService.getEmployeesByDesignation(designation);
    }

    @Tool(description = "Get all active employees")
    public List<Employee> getAllActiveEmployees() {
        return employeeService.getActiveEmployees();
    }

    @Tool(description = "Count all employees")
    public long countEmployees() {
        return employeeService.countEmployees();
    }

    @Tool(description = "Count employees by department")
    public long countEmployeeByDepartment(String department) {
        return employeeService.countEmployeesByDepartment(department);
    }
}
