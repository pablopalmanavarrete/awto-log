package cl.awto.hastag.services;

import cl.awto.hastag.entities.Hashtag;
import cl.awto.hastag.entities.Logger;
import cl.awto.hastag.entities.LoggerHashtag;
import cl.awto.hastag.repository.HashtagRepository;
import cl.awto.hastag.repository.LoggerHashtagRepository;
import cl.awto.hastag.repository.LoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HashServicesImpl implements HashServices {
    @Autowired
    private LoggerRepository loggerRepository;

    @Autowired
    private HashtagRepository hashtagRepository;

    @Autowired
    private LoggerHashtagRepository loggerHashtagRepository;

    @Override
    public Logger crearLogger(Logger logger) {
        return loggerRepository.save(logger);
    }

    @Override
    public Hashtag findHashtag(String hashtag) {
        return hashtagRepository.findByDescription(hashtag);
    }

    @Override
    public LoggerHashtag crearHashLogger(LoggerHashtag logHash) {
        return loggerHashtagRepository.save(logHash);
    }

    @Override
    public Hashtag crearHash(Hashtag hashtag) {
        return hashtagRepository.save(hashtag);
    }
}
