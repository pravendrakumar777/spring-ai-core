package com.niiran.engineering.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "employee_audit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private String operation;
    @Lob
    private String description;
    private LocalDateTime operationTime;
    @PrePersist
    public void prePersist() {
        operationTime = LocalDateTime.now();
    }

}
