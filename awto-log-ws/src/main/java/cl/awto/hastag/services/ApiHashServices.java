package cl.awto.hastag.services;

import cl.awto.hastag.vo.RequestHashtag;
import cl.awto.hastag.vo.RequestLogger;
import org.springframework.http.ResponseEntity;

public interface ApiHashServices {
    ResponseEntity<Integer> createLog(RequestLogger request);

    ResponseEntity<Integer> update(RequestHashtag requestHashtag);
}
