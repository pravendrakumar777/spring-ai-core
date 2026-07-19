package com.niiran.engineering.repository;

import com.niiran.engineering.entity.AgentChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentChatHistoryRepository extends JpaRepository<AgentChatHistory, Long> {

}
