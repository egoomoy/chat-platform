package aehdb.chatbot.model.repository;


import aehdb.chatbot.model.entity.ChatbotOptionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatbotOptionRepository extends JpaRepository<ChatbotOptionInfo, Long> {
}
