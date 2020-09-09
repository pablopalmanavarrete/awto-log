package cl.awto.hastag.repository;

import cl.awto.hastag.entities.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {
    List<Hashtag> findByDescription(String description);
}
