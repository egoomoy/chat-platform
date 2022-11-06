package aehdb.chatbot.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aehdb.chatbot.model.entity.ChatbotConversation;


public interface ChatbotConversationRepository extends JpaRepository<ChatbotConversation, Long> {

    @Query("select p.id from #{#entityName} p WHERE parent_chatbot_conversation_id is  null")
    List<Long> getAllIds();


    @Query("select p.seq from #{#entityName} p WHERE parent_chatbot_conversation_id is  null")
    List<Long> getAllseqs();
//
//    @Query("SELECT DISTINCT c FROM ChatbotConversation c JOIN FETCH c.chatbotOptionInfo mI JOIN FETCH m.posts p")
//    List<Member> findByMemberIdsIn(@Param("memberIds") List<Long> memberIds);
}
