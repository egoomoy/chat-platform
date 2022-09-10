package aehdb.chatbot.model.repository;

import aehdb.chatbot.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author MalkeithSingh on 11-09-2019
 */
@Repository
public interface PersonRepo extends JpaRepository<Person,Long> {

}