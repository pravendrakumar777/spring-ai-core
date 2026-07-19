package com.niiran.engineering.service.impl;

import com.niiran.engineering.dto.ChatRequest;
import com.niiran.engineering.dto.ChatResponse;
import com.niiran.engineering.service.AgentChatHistoryService;
import com.niiran.engineering.service.AgentExecutionLogService;
import com.niiran.engineering.service.AgentService;
import com.niiran.engineering.tools.EmployeeTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AgentServiceImpl implements AgentService {

    private static final Logger log = LoggerFactory.getLogger(AgentServiceImpl.class);
    private final ChatClient chatClient;
    private final EmployeeTool employeeTool;
    private final AgentChatHistoryService agentChatHistoryService;
    private final AgentExecutionLogService agentExecutionLogService;

    public AgentServiceImpl(ChatClient.Builder chatClientBuilder, EmployeeTool employeeTool, AgentChatHistoryService agentChatHistoryService, AgentExecutionLogService agentExecutionLogService) {
        this.chatClient = chatClientBuilder.build();
        this.employeeTool = employeeTool;
        this.agentChatHistoryService = agentChatHistoryService;
        this.agentExecutionLogService = agentExecutionLogService;
    }

    @Override
    public ChatResponse chat(ChatRequest prompt) {
        log.info("========== AI Agent Request Started ==========");
        log.info("User Prompt : {}", prompt);
        try {
            log.info("SENDING REQUEST TO Ollama MODEL...");
            long startTime = System.currentTimeMillis();
            long executionTime = System.currentTimeMillis() - startTime;
            String apiResponse = chatClient.prompt()
                    .system("""
                            You are
                            an AI Employee Management Assistant.
                            You must
                            use available tools whenever the user ask to:
                            - Create Employee
                            - Update Employee
                            - Delete Employee
                            - Fetch Employee
                            - List Employees
                            - Count Employees
                            Never make up employee information.
                            Always call the appropriate tool.
                            """)
                    .user(prompt.getMessage())
                    .tools(employeeTool)
                    .call()
                    .content();
            log.info("AI RESPONSE : {}", apiResponse);
            agentChatHistoryService.saveChat(prompt.getMessage(), apiResponse, "AI_AGENT");
            log.info("Chat history saved successfully.");
            agentExecutionLogService.saveLog("EmployeeTool", prompt.getMessage(), apiResponse, "SUCCESS");
            log.info("Execution log saved successfully.");
            log.info("========== AI Agent Request Completed ==========");
            return ChatResponse.builder()
                    .userMessage(prompt.getMessage())
                    .aiResponse(apiResponse)
                    .status("SUCCESS")
                    .model("llama3.2")
                    .executionTime(executionTime)
                    .build();
        } catch (Exception ex) {
            log.error("AI Agent execution failed.", ex);
            agentExecutionLogService.saveLog("EmployeeTool", prompt.getMessage(), ex.getMessage(), "FAILED");
            throw new RuntimeException("AI Agent failed.", ex);
        }
    }
}
