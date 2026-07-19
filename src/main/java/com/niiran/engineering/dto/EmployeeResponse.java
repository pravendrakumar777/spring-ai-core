package com.niiran.engineering.dto;

public class EmployeeResponse {

    private Long id;
    private String employeeName;
    private String email;
    private String department;
    private String designation;
    private Double salary;
    private String address;
    private Boolean active;

    public EmployeeResponse() {
    }

    public EmployeeResponse(Long id, String employeeName, String email,
                            String department, String designation,
                            Double salary, String address, Boolean active) {
        this.id = id;
        this.employeeName = employeeName;
        this.email = email;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.address = address;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
