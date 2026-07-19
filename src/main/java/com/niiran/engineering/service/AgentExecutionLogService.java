package com.niiran.engineering.service;

import com.niiran.engineering.entity.AgentExecutionLog;

import java.util.List;

public interface AgentExecutionLogService {

    AgentExecutionLog saveLog(String toolName, String request, String response, String status);
    List<AgentExecutionLog> getAllLogs();
}
