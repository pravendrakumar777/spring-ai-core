package com.niiran.engineering.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.niiran.engineering.dto.ChatRequest;
import com.niiran.engineering.dto.ChatResponse;
import com.niiran.engineering.service.AgentService;
import com.niiran.engineering.agents.supervisor.SupervisorAgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/agents/chats")
public class AgentController {
    private static final Logger log = LoggerFactory.getLogger(AgentController.class);
    private final AgentService agentService;
    private final SupervisorAgentService supervisorAgentService;
    public AgentController(AgentService agentService, SupervisorAgentService supervisorAgentService) {
        this.agentService = agentService;
        this.supervisorAgentService = supervisorAgentService;
    }

    @PostMapping("/prompt/v1")
    public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) throws JsonProcessingException {
        log.info("========== AI Agent Request ==========");
        log.info("User Prompt: {}", request.getMessage());
        ChatResponse response = agentService.chat(request);
        log.info("========== AI Agent Request Completed ==========");
        return ResponseEntity.ok(response);
    }
    @PostMapping("/prompt/v2")
    public ResponseEntity<ChatResponse> superChat(@RequestBody ChatRequest request) {
        log.info("========== AI Agent Request ==========");
        log.info("Client Prompt: {}", request.getMessage());
        ChatResponse chatResponse = supervisorAgentService.chat(request);
        log.info("========== AI Agent Request Completed ==========");
        log.info(" Agent Response: {}", request.getMessage());
        return ResponseEntity.ok(chatResponse);
    }
}
