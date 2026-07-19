package com.niiran.engineering.service.impl;

import com.niiran.engineering.dto.ChatRequest;
import com.niiran.engineering.dto.ChatResponse;
import com.niiran.engineering.entity.ChatHistory;
import com.niiran.engineering.repository.ChatHistoryRepository;
import com.niiran.engineering.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatClient chatClient;
    private final ChatHistoryRepository chatHistoryRepository;

    public ChatServiceImpl(ChatClient chatClient, ChatHistoryRepository chatHistoryRepository) {
        this.chatClient = chatClient;
        this.chatHistoryRepository = chatHistoryRepository;
    }


    @Override
    public ChatResponse chat(ChatRequest request) {
        long startTime = System.currentTimeMillis();

        String aiResponse = chatClient.prompt()
                .user(request.getMessage())
                .call()
                .content();

        long executionTime = System.currentTimeMillis() - startTime;

        ChatHistory history = new ChatHistory();
        history.setUserMessage(request.getMessage());
        history.setAiResponse(aiResponse);
        history.setExecutionTime(executionTime);
        history.setModel("llama3.2");
        chatHistoryRepository.save(history);

        return ChatResponse.builder()
                .userMessage(request.getMessage())
                .aiResponse(aiResponse)
                .status("SUCCESS")
                .model("llama3.2")
                .executionTime(executionTime)
                .build();

    }
}
