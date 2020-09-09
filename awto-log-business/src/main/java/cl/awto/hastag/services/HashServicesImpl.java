package cl.awto.hastag.services;

import cl.awto.hastag.entities.Hashtag;
import cl.awto.hastag.entities.Logger;
import cl.awto.hastag.entities.LoggerHashtag;
import cl.awto.hastag.repository.HashtagRepository;
import cl.awto.hastag.repository.LoggerHashtagRepository;
import cl.awto.hastag.repository.LoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Hashtag> findHashtag(String hashtag) {
        return hashtagRepository.findByDescription(hashtag);
    }

    @Override
    public LoggerHashtag crearHashLogger(LoggerHashtag logHash) {
        return loggerHashtagRepository.save(logHash);
    }

    @Override
    public Hashtag saveOrUpdateHash(Hashtag hashtag) {
        return hashtagRepository.save(hashtag);
    }

    @Override
    public Optional<Hashtag> findHashById(int id) {
        return hashtagRepository.findById(id);
    }

    @Override
    public List<LoggerHashtag> findAll(){
        return loggerHashtagRepository.findAll();
    }

    @Override
    public List<LoggerHashtag>  findById(int idLog){
        return loggerHashtagRepository.findByLogger_Id(idLog);
    }

    @Override
    public List<LoggerHashtag> findByHashtag(String hashstag){
        return loggerHashtagRepository.findByHashtag_Description(hashstag);
    }
}
