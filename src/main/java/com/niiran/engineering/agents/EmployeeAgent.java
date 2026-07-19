package com.niiran.engineering.agents;

import com.niiran.engineering.dto.ChatRequest;
import com.niiran.engineering.dto.ChatResponse;
import com.niiran.engineering.tools.EmployeeTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAgent {

    private final ChatClient chatClient;
    private final EmployeeTool employeeTool;

    public EmployeeAgent(ChatClient chatClient, EmployeeTool employeeTool) {
        this.chatClient = chatClient;
        this.employeeTool = employeeTool;
    }

    public ChatResponse chat(ChatRequest request) {
        String response = chatClient.prompt()
                .system("""
                        You are Employee AI.

                        Always use EmployeeTool.

                        Never fabricate employee information.
                        """)
                .user(request.getMessage())
                .tools(employeeTool)
                .call()
                .content();

        return ChatResponse.builder()
                .aiResponse(response)
                .build();
    }
}
