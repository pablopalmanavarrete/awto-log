package cl.awto.hastag.services;

import cl.awto.hastag.entities.Hashtag;
import cl.awto.hastag.entities.Logger;
import cl.awto.hastag.entities.LoggerHashtag;

import java.util.List;
import java.util.Optional;

public interface HashServices {
    Logger crearLogger(Logger logger);

    List<Hashtag> findHashtag(String hashtag);

    LoggerHashtag crearHashLogger(LoggerHashtag logHash);

    Hashtag saveOrUpdateHash(Hashtag hashtag);

    Optional<Hashtag> findHashById(Integer id);
}
