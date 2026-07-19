package com.niiran.engineering.agents;

import com.niiran.engineering.dto.ChatRequest;
import com.niiran.engineering.dto.ChatResponse;
import com.niiran.engineering.tools.ProductTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ProductAgent {

    private static final Logger log = LoggerFactory.getLogger(ProductAgent.class);
    private final ChatClient chatClient;
    private final ProductTool productTool;

    public ProductAgent(ChatClient chatClient, ProductTool productTool) {
        this.chatClient = chatClient;
        this.productTool = productTool;
    }

    public ChatResponse chat(ChatRequest request) {
        log.info("========== PRODUCT AGENT STARTED ==========");
        log.info("Product Request : {}", request.getMessage());
        long startTime = System.currentTimeMillis();

        try {
            String response = chatClient.prompt()
                    .system("""
                        You are an AI Product Management Assistant.

                        You must use ProductTool whenever the user asks to:

                        - Create Product
                        - Update Product
                        - Delete Product
                        - Find Product
                        - List Products
                        - Count Products

                        Never make up product information.

                        Always call ProductTool.
                        """)

                    .user(request.getMessage())
                    .tools(productTool)
                    .call()
                    .content();

            long executionTime = System.currentTimeMillis() - startTime;
            log.info("AI Response : {}", response);
            log.info("Execution Time : {} ms", executionTime);
            log.info("========== PRODUCT AGENT COMPLETED ==========");

            return ChatResponse.builder()
                    .userMessage(request.getMessage())
                    .aiResponse(response)
                    .status("SUCCESS")
                    .model("llama3.2")
                    .executionTime(executionTime)
                    .build();

        } catch (Exception ex) {
            long executionTime = System.currentTimeMillis() - startTime;

            log.error("Product Agent Error", ex);
            log.error("Execution Time : {} ms", executionTime);

            return ChatResponse.builder()
                    .userMessage(request.getMessage())
                    .aiResponse("Failed to process product request.")
                    .status("FAILED")
                    .model("llama3.2")
                    .executionTime(executionTime)
                    .build();
        }
    }
}
