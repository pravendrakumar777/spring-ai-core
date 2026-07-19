package com.niiran.engineering.repository;

import com.niiran.engineering.entity.AgentExecutionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentExecutionLogRepository extends JpaRepository<AgentExecutionLog, Long> {
    List<AgentExecutionLog> findByToolName(String toolName);
    List<AgentExecutionLog> findByStatus(String status);

}
