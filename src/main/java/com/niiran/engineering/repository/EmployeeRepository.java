package com.niiran.engineering.repository;

import com.niiran.engineering.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
    List<Employee> findByDepartment(String department);
    List<Employee> findByDesignation(String designation);
    List<Employee> findByActive(Boolean active);
    boolean existsByEmail(String email);
    long countByDepartment(String department);

}
