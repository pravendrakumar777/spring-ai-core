package com.niiran.engineering.service.impl;

import com.niiran.engineering.entity.Employee;
import com.niiran.engineering.exception.ResourceNotFoundException;
import com.niiran.engineering.repository.EmployeeAuditRepository;
import com.niiran.engineering.repository.EmployeeRepository;
import com.niiran.engineering.service.EmployeeAuditService;
import com.niiran.engineering.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeAuditService employeeAuditService;
    private final EmployeeAuditRepository employeeAuditRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeAuditService employeeAuditService, EmployeeAuditRepository employeeAuditRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeAuditService = employeeAuditService;
        this.employeeAuditRepository = employeeAuditRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
//        if (employeeRepository.existsByEmail(employee.getEmail())) {
//            throw new RuntimeException("Employee already exists with email : " + employee.getEmail());
//        }
        Employee savedEmployee = employeeRepository.save(employee);
        employeeAuditService.saveAudit(savedEmployee.getId(), "CREATE", "Employee created successfully.");
        return savedEmployee;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id : " + id));
        existingEmployee.setEmployeeName(employee.getEmployeeName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setDesignation(employee.getDesignation());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setActive(employee.getActive());
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        employeeAuditService.saveAudit(updatedEmployee.getId(), "UPDATE", "Employee update successfully.");
        return updatedEmployee;
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));
        employeeRepository.delete(employee);
        employeeAuditService.saveAudit(id, "DELETE", "Employee deleted successfully.");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesByDesignation(String designation) {
        return employeeRepository.findByDesignation(designation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getActiveEmployees() {
        return employeeRepository.findByActive(true);
    }

    @Override
    @Transactional(readOnly = true)
    public long countEmployees() {
        return employeeRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public long countEmployeesByDepartment(String department) {
        return employeeRepository.countByDepartment(department);
    }
}
