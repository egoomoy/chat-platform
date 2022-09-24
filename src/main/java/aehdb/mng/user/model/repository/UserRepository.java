package aehdb.mng.user.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aehdb.mng.legacy.model.entity.Legacy;
import aehdb.mng.user.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u left join fetch u.role where u.accntId = :accntId")
	User findByAccntId(@Param("accntId") String accntId);

	@Query("select u from User u left join fetch u.legacy where u.legacy.id = :#{#paramLegacy.id} ")
	List<User> findByLegacyId(@Param(value = "paramLegacy") Legacy legacy , Pageable pageable);

	@Query("select u from User u left join fetch u.role where u.id = :id")
	User findOneById(@Param("id") Long id);


//	// JPQL 객체 파라미터 쿼리
//		@Query(value = "select sn from Snack sn where sn.id > :#{#paramSnack.id}")
//		public List<Snack> selectJPQLById3(@Param(value = "paramSnack") Snack snack);

}
