package com.niiran.engineering.repository;

import com.niiran.engineering.entity.EmployeeAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeAuditRepository extends JpaRepository<EmployeeAudit, Long> {
    List<EmployeeAudit> findEmployeeById(Long employeeId);
    List<EmployeeAudit> findByOperation(String operation);
}
