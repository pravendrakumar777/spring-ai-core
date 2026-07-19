package com.niiran.engineering.mapper;

import com.niiran.engineering.dto.ChatResponse;
import com.niiran.engineering.entity.ChatHistory;
import org.springframework.stereotype.Component;

@Component
public class ChatHistoryMapper {
    public ChatHistory toEntity(ChatResponse response) {
        if (response == null) {
            return null;
        }

        return ChatHistory.builder()
                .userMessage(response.getUserMessage())
                .aiResponse(response.getAiResponse())
                .executionTime(response.getExecutionTime())
                .model(response.getModel())
                .build();

    }

    public ChatResponse toResponse(ChatHistory history) {
        if (history == null) {
            return null;
        }
        return ChatResponse.builder()
                .userMessage(history.getUserMessage())
                .aiResponse(history.getAiResponse())
                .executionTime(history.getExecutionTime())
                .model(history.getModel())
                .status("SUCCESS")
                .build();
    }
}
