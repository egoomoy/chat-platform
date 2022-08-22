package aehdb.mng.legacy.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aehdb.mng.legacy.model.entity.Legacy;

@Repository
public interface LegacyRepository extends JpaRepository<Legacy, Long> {

}
