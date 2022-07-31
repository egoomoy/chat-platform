package aehdb.mng.user.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aehdb.mng.user.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u left join fetch u.role where u.accntId = :accntId")
	Optional<User> findByAccntId(@Param("accntId") String accntId);

}
