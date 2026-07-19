package com.niiran.engineering.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "agent_execution_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentExecutionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String toolName;
    @Lob
    private String request;
    @Lob
    private String response;
    private String status;
    private LocalDateTime executionTime;
    @PrePersist
    public void prePersist() {
        executionTime = LocalDateTime.now();
    }

}
