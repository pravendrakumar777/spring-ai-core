package com.niiran.engineering.service;

import com.niiran.engineering.dto.ChatRequest;
import com.niiran.engineering.dto.ChatResponse;

public interface AgentService {
    ChatResponse chat(ChatRequest prompt);
}
