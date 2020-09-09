package cl.awto.hastag.services;

import cl.awto.hastag.vo.RequestHashtag;
import cl.awto.hastag.vo.RequestLogger;
import cl.awto.hastag.vo.ResponseLogger;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface ApiHashServices {
    ResponseEntity<Integer> createLog(RequestLogger request);

    ResponseEntity<Integer> update(RequestHashtag requestHashtag);

    ResponseEntity<Collection<ResponseLogger>> findAllLoggers();

    ResponseEntity<Collection<ResponseLogger>> findByHashtag(String hashtag);

    ResponseEntity<ResponseLogger> findByLogId(int logId);
}
