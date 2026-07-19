package com.niiran.engineering.agents.supervisor;

import com.niiran.engineering.dto.ChatRequest;
import com.niiran.engineering.dto.ChatResponse;
import com.niiran.engineering.entity.enums.AgentType;
import com.niiran.engineering.service.AgentService;
import com.niiran.engineering.agents.EmployeeAgent;
import com.niiran.engineering.agents.OrderAgent;
import com.niiran.engineering.agents.ProductAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class SupervisorAgentService implements AgentService {

    private static final Logger log = LoggerFactory.getLogger(SupervisorAgentService.class);
    private final ChatClient chatClient;
    private final EmployeeAgent employeeAgent;
    private final ProductAgent productAgent;
    private final OrderAgent orderAgent;

    public SupervisorAgentService(ChatClient chatClient, EmployeeAgent employeeAgent, ProductAgent productAgent, OrderAgent orderAgent) {
        this.chatClient = chatClient;
        this.employeeAgent = employeeAgent;
        this.productAgent = productAgent;
        this.orderAgent = orderAgent;
    }

    @Override
    public ChatResponse chat(ChatRequest request) {
        AgentType agent = detectAgent(request.getMessage());

        return switch (agent) {
            case EMPLOYEE -> employeeAgent.chat(request);
            case PRODUCT -> productAgent.chat(request);
            case ORDER -> orderAgent.chat(request);
            case GENERAL -> ChatResponse.builder()
                    .userMessage(request.getMessage())
                    .aiResponse("I'm your AI Assistant. How can I help you?")
                    .status("SUCCESS")
                    .build();
            default -> ChatResponse.builder()
                    .userMessage(request.getMessage())
                    .aiResponse("Sorry, I couldn't determine which agent should handle your request.")
                    .status("FAILED")
                    .build();
        };
    }

    private AgentType detectAgent(String message) {
        String response = chatClient.prompt()
                .system("""
                    You are a routing classifier.

                    Your job is ONLY to classify the request.

                    Valid outputs:
                    EMPLOYEE
                    PRODUCT
                    ORDER
                    GENERAL

                    Rules:
                    - Return ONLY one word.
                    - No explanation.
                    - No punctuation.
                    - No markdown.
                    - Return exactly one of:
                      EMPLOYEE
                      PRODUCT
                      ORDER
                      GENERAL
                    """)
                .user(message)
                .call()
                .content();

        log.info("Original AI Response: [{}]", response);

        response = response
                .trim()
                .replace(".", "")
                .replace(",", "")
                .replace("`", "")
                .toUpperCase();

        log.info("Cleaned AI Response: [{}]", response);
        try {
            return AgentType.valueOf(response);
        } catch (Exception e) {
            log.error("Invalid Agent Type: {}", response, e);
            return AgentType.UNKNOWN;
        }
    }
}
