package cl.awto.hastag.services;

import cl.awto.hastag.entities.Hashtag;
import cl.awto.hastag.entities.Logger;
import cl.awto.hastag.entities.LoggerHashtag;
import cl.awto.hastag.vo.RequestHashtag;
import cl.awto.hastag.vo.RequestLogger;
import cl.awto.hastag.vo.ResponseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Service
public class ApiHashServicesImpl implements ApiHashServices {

    @Autowired
    HashServices hashServices;

    @Override
    @Transactional
    public ResponseEntity<Integer> createLog(RequestLogger request) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        if (request == null || request.getHashtags() == null || request.getHashtags().isEmpty()) {
            headers.add("message", "Faltan datos para crear el log");
            return new ResponseEntity<>(null, headers, BAD_REQUEST);
        }

        final Logger logger = new Logger();

        logger.setHost(request.getHost());
        logger.setCreation_date(Instant.now());
        logger.setOrigin(request.getOrigin());
        logger.setDetails(request.getDetails());
        logger.setStacktrace(request.getStacktrace());

        hashServices.crearLogger(logger);

        request.getHashtags().forEach(h -> {
            List<Hashtag> results = hashServices.findHashtag(h);

            LoggerHashtag logHash = new LoggerHashtag();

            if (results.isEmpty()) {
                Hashtag hashtag = new Hashtag();
                hashtag.setDescription(validateTag(h));

                hashServices.saveOrUpdateHash(hashtag);
                logHash.setHashtag(hashtag);
            } else {
                logHash.setHashtag(results.stream().findFirst().get());
            }
            logHash.setLogger(logger);
            hashServices.crearHashLogger(logHash);
        });

        headers.add("message", "Se ha grabado correctamente");
        return new ResponseEntity<>(logger.getId(), OK);
    }

    @Override
    public ResponseEntity<Integer> update(RequestHashtag requestHashtag) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        if (requestHashtag == null || requestHashtag.getDescription() == null || requestHashtag.getDescription().isEmpty()) {
            headers.add("message", "Faltan datos para actualizar el hashstag");
            return new ResponseEntity<>(headers, BAD_REQUEST);
        }

        requestHashtag.setDescription(validateTag(requestHashtag.getDescription()));

        List<Hashtag> hashtags = hashServices.findHashtag(requestHashtag.getDescription());

        if (!hashtags.isEmpty()) {
            headers.add("message", "Hashstag est\u00e1 repetido en sistema");
            return new ResponseEntity<>(headers, BAD_REQUEST);
        }

        Optional<Hashtag> hashBd = hashServices.findHashById(requestHashtag.getId());

        if (hashBd.isPresent()) {
            Hashtag hash = hashBd.get();
            hash.setDescription(requestHashtag.getDescription());
            hashServices.saveOrUpdateHash(hash);

            headers.add("message", "Se ha actualizado de forma correcta el hashstag");
            return new ResponseEntity<>(hash.getId(), OK);
        }

        headers.add("message", "Hashstag no encontrado en sistema");
        return new ResponseEntity<>(headers, BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Collection<ResponseLogger>> findAllLoggers() {
        List<LoggerHashtag> loggerHashtags = hashServices.findAll();

        if (loggerHashtags.isEmpty())
            return new ResponseEntity<>(new ArrayList<>(), OK);

        HashMap<Integer, ResponseLogger> loggersMap = convertLoggerHash2Response(loggerHashtags);

        return new ResponseEntity<>(loggersMap.values(), OK);
    }

    @Override
    public ResponseEntity<Collection<ResponseLogger>> findByHashtag(String hashtag) {
        List<LoggerHashtag> loggerHashtags = hashServices.findByHashtag(validateTag(hashtag));

        if (loggerHashtags.isEmpty())
            return new ResponseEntity<>(new ArrayList<>(), OK);

        HashMap<Integer, ResponseLogger> loggersMap = convertLoggerHash2Response(loggerHashtags);

        return new ResponseEntity<>(loggersMap.values(), OK);
    }

    @Override
    public ResponseEntity<ResponseLogger> findByLogId(int logId) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        List<LoggerHashtag> loggerHashtags = hashServices.findById(logId);

        if (loggerHashtags.isEmpty())
            return new ResponseEntity<>(null, BAD_REQUEST);

        HashMap<Integer, ResponseLogger> loggersMap = convertLoggerHash2Response(loggerHashtags);
        if (loggersMap.size() == 1) {
            return new ResponseEntity<>(loggersMap.get(loggerHashtags.get(0).getLogger().getId()), OK);
        }

        headers.add("message", "Se ha encontrado mas de un Logger con el mismo ID");
        return new ResponseEntity<>(null, headers, BAD_REQUEST);
    }

    private HashMap<Integer, ResponseLogger> convertLoggerHash2Response(List<LoggerHashtag> loggerHashtags) {
        HashMap<Integer, ResponseLogger> loggersMap = new HashMap<>();
        for (LoggerHashtag logHash : loggerHashtags) {
            if (loggersMap.containsKey(logHash.getLogger().getId())) {
                loggersMap.get(logHash.getLogger().getId()).getHashtags().add(logHash.getHashtag().getDescription());
            } else {
                loggersMap.put(logHash.getLogger().getId(), new ResponseLogger());
                loggersMap.get(logHash.getLogger().getId()).setId(logHash.getLogger().getId());
                loggersMap.get(logHash.getLogger().getId()).setHost(logHash.getLogger().getHost());
                loggersMap.get(logHash.getLogger().getId()).setOrigin(logHash.getLogger().getOrigin());
                loggersMap.get(logHash.getLogger().getId()).setDetails(logHash.getLogger().getDetails());
                loggersMap.get(logHash.getLogger().getId()).setStacktrace(logHash.getLogger().getStacktrace());

                loggersMap.get(logHash.getLogger().getId()).setHashtags(new ArrayList<>());
                loggersMap.get(logHash.getLogger().getId()).getHashtags().add(logHash.getHashtag().getDescription());
            }
        }
        return loggersMap;
    }

    private String validateTag(String inputTag) {
        return inputTag.startsWith("#")
                ? inputTag :
                "#" + inputTag;
    }
}
