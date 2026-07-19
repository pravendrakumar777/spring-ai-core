package com.niiran.engineering.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {

    private String userMessage;
    private String aiResponse;
    private String status;
    private String model;
    private Long executionTime;
}
