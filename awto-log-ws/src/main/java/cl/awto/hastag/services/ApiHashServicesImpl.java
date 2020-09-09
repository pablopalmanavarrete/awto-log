package cl.awto.hastag.services;

import cl.awto.hastag.entities.Hashtag;
import cl.awto.hastag.entities.Logger;
import cl.awto.hastag.entities.LoggerHashtag;
import cl.awto.hastag.vo.RequestLogger;
import cl.awto.hastag.vo.ResponseId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;

@Service
public class ApiHashServicesImpl implements ApiHashServices {

    @Autowired
    HashServices hashServices;

    @Override
    @Transactional
    public ResponseEntity<ResponseId> createLog(RequestLogger request) {
        if (request == null || request.getHashtags() == null || request.getHashtags().isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        final Logger logger = new Logger();

        logger.setHost(request.getHost());
        logger.setCreation_date(Instant.now());
        logger.setOrigin(request.getOrigin());
        logger.setDetails(request.getDetails());
        logger.setStacktrace(request.getStacktrace());

        hashServices.crearLogger(logger);

        request.getHashtags().forEach(h -> {
            Hashtag hashtag = hashServices.findHashtag(h);
            if (hashtag == null) {
                hashtag = new Hashtag();
                hashtag.setDescription(h);

                hashServices.crearHash(hashtag);
            }

            LoggerHashtag logHash = new LoggerHashtag();
            logHash.setHashtag(hashtag);
            logHash.setLogger(logger);
            hashServices.crearHashLogger(logHash);
        });

        ResponseId response = new ResponseId();
        response.setId(logger.getId());


        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
