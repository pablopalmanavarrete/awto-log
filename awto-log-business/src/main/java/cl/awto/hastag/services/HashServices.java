package cl.awto.hastag.services;

import cl.awto.hastag.entities.Hashtag;
import cl.awto.hastag.entities.Logger;
import cl.awto.hastag.entities.LoggerHashtag;

public interface HashServices {
    Logger crearLogger(Logger logger);

    Hashtag findHashtag(String hashtag);

    LoggerHashtag crearHashLogger(LoggerHashtag logHash);

    Hashtag crearHash(Hashtag hashtag);
}
