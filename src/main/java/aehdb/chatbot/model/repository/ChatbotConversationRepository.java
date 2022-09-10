package aehdb.chatbot.model.repository;

import aehdb.chatbot.model.entity.Board2;
import aehdb.chatbot.model.entity.ChatbotConversation;
import aehdb.mng.board.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ChatbotConversationRepository extends JpaRepository<ChatbotConversation, Long> {
//
//    @Query("SELECT DISTINCT c FROM ChatbotConversation c JOIN FETCH c.chatbotOptionInfo mI JOIN FETCH m.posts p")
//    List<Member> findByMemberIdsIn(@Param("memberIds") List<Long> memberIds);
}
