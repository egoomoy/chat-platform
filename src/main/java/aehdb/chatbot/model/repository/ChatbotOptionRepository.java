package aehdb.chatbot.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatbotOptionRepository extends JpaRepository<ChatbotOptionInfo, Long> {
}
