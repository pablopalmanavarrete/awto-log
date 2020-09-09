package cl.awto.hastag.repository;

import cl.awto.hastag.entities.LoggerHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerHashtagRepository extends JpaRepository<LoggerHashtag, Integer> {
}
