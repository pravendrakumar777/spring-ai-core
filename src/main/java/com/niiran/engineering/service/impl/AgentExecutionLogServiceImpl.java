package com.niiran.engineering.service.impl;

import com.niiran.engineering.entity.AgentExecutionLog;
import com.niiran.engineering.repository.AgentExecutionLogRepository;
import com.niiran.engineering.service.AgentExecutionLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AgentExecutionLogServiceImpl implements AgentExecutionLogService {
    private final AgentExecutionLogRepository agentExecutionLogRepository;

    public AgentExecutionLogServiceImpl(AgentExecutionLogRepository agentExecutionLogRepository) {
        this.agentExecutionLogRepository = agentExecutionLogRepository;
    }

    @Override
    public AgentExecutionLog saveLog(String toolName, String request, String response, String status) {
        AgentExecutionLog executionLog = new AgentExecutionLog();
        executionLog.setToolName(toolName);
        executionLog.setRequest(request);
        executionLog.setResponse(response);
        executionLog.setStatus(status);
        return agentExecutionLogRepository.save(executionLog);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgentExecutionLog> getAllLogs() {
        return agentExecutionLogRepository.findAll();
    }
}
