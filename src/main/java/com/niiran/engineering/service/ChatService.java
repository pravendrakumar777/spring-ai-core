package com.niiran.engineering.service;

import com.niiran.engineering.dto.ChatRequest;
import com.niiran.engineering.dto.ChatResponse;
import reactor.core.publisher.Flux;

public interface ChatService {
    /**
     * Process user chat request using Spring AI.
     *
     * @param request Chat request
     * @return Chat response
     */
    ChatResponse chat(ChatRequest request);
}
