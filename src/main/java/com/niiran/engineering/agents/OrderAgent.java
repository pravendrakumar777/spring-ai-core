package com.niiran.engineering.agents;

import com.niiran.engineering.dto.ChatRequest;
import com.niiran.engineering.dto.ChatResponse;
import com.niiran.engineering.tools.OrderTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OrderAgent {
    private final ChatClient chatClient;
    private final OrderTool orderTool;

    public OrderAgent(ChatClient chatClient, OrderTool orderTool) {
        this.chatClient = chatClient;
        this.orderTool = orderTool;
    }

    public ChatResponse chat(ChatRequest request) {
        String response = chatClient.prompt()
                .system("""
                        You are an AI Order Management Assistant.

                        You must use OrderTool whenever the user asks to:

                        - Create Order
                        - Update Order
                        - Cancel Order
                        - Get Order
                        - List Orders
                        - Count Orders

                        Never invent order details.

                        Always use OrderTool.
                        """)

                .user(request.getMessage())
                .tools(orderTool)
                .call()
                .content();
        return ChatResponse.builder()
                .userMessage(request.getMessage())
                .aiResponse(response)
                .status("SUCCESS")
                .model("llama3.2")
                .build();
    }
}
