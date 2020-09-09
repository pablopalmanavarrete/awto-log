package cl.awto.hastag.services;

import cl.awto.hastag.vo.RequestLogger;
import cl.awto.hastag.vo.ResponseId;
import org.springframework.http.ResponseEntity;

public interface ApiHashServices {
    ResponseEntity<ResponseId> createLog(RequestLogger request);
}
