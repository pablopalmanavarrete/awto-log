package cl.awto.hastag.repository;

import cl.awto.hastag.entities.LoggerHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoggerHashtagRepository extends JpaRepository<LoggerHashtag, Integer> {

    List<LoggerHashtag> findByLogger_Id(int id);

    List<LoggerHashtag> findByHashtag_Description(String description);
}
