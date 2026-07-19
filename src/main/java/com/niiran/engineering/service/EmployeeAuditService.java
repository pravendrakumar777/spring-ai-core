package com.niiran.engineering.service;

import com.niiran.engineering.entity.EmployeeAudit;

import java.util.List;

public interface EmployeeAuditService {

    EmployeeAudit saveAudit(Long employeeId, String operation, String description);
    List<EmployeeAudit> getEmployeeAudit(Long employeeId);
}
