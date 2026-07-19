package com.niiran.engineering.service;

import com.niiran.engineering.entity.AgentChatHistory;

import java.util.List;

public interface AgentChatHistoryService {
    AgentChatHistory saveChat(String prompt, String response, String action);
    List<AgentChatHistory> getAllChats();
}
