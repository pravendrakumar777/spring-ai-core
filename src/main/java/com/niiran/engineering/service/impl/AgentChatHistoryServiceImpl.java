package com.niiran.engineering.service.impl;

import com.niiran.engineering.entity.AgentChatHistory;
import com.niiran.engineering.repository.AgentChatHistoryRepository;
import com.niiran.engineering.service.AgentChatHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AgentChatHistoryServiceImpl implements AgentChatHistoryService {
    private final AgentChatHistoryRepository agentChatHistoryRepository;

    public AgentChatHistoryServiceImpl(AgentChatHistoryRepository agentChatHistoryRepository) {
        this.agentChatHistoryRepository = agentChatHistoryRepository;
    }

    @Override
    public AgentChatHistory saveChat(String prompt, String response, String action) {
        AgentChatHistory chatHistory = new AgentChatHistory();
        chatHistory.setUserPrompt(prompt);
        chatHistory.setApiResponse(response);
        chatHistory.setActionPerformed(action);
        return agentChatHistoryRepository.save(chatHistory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgentChatHistory> getAllChats() {
        return agentChatHistoryRepository.findAll();
    }
}
