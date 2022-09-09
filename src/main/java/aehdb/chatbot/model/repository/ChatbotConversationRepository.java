package aehdb.chatbot.model.repository;

import aehdb.chatbot.model.entity.Board2;
import aehdb.chatbot.model.entity.ChatbotConversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatbotConversationRepository extends JpaRepository<ChatbotConversation, Long> {
}
