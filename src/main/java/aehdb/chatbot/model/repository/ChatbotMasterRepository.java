package aehdb.chatbot.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aehdb.chatbot.model.entity.ChatbotMessage;

@Repository
public interface ChatbotMasterRepository extends JpaRepository<ChatbotMessage,Long> {
	
	ChatbotMessage findChatbotMessageById(Long id);


}