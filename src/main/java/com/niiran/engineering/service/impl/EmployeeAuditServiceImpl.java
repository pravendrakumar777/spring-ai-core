package com.niiran.engineering.service.impl;

import com.niiran.engineering.entity.EmployeeAudit;
import com.niiran.engineering.repository.EmployeeAuditRepository;
import com.niiran.engineering.service.EmployeeAuditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeAuditServiceImpl implements EmployeeAuditService {

    private final EmployeeAuditRepository employeeAuditRepository;

    public EmployeeAuditServiceImpl(EmployeeAuditRepository employeeAuditRepository) {
        this.employeeAuditRepository = employeeAuditRepository;
    }

    @Override
    public EmployeeAudit saveAudit(Long employeeId, String operation, String description) {
        EmployeeAudit employeeAudit = new EmployeeAudit();
        employeeAudit.setEmployeeId(employeeId);
        employeeAudit.setOperation(operation);
        employeeAudit.setDescription(description);
        return employeeAuditRepository.save(employeeAudit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeAudit> getEmployeeAudit(Long employeeId) {
        return employeeAuditRepository.findEmployeeById(employeeId);
    }
}
