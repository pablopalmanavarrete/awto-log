package cl.awto.hastag.controller;

import cl.awto.hastag.services.ApiHashServices;
import cl.awto.hastag.vo.RequestHashtag;
import cl.awto.hastag.vo.RequestLogger;
import cl.awto.hastag.vo.ResponseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/")
public class LoggerController {

    @Autowired
    private ApiHashServices apiHashServices;

    @PostMapping(value = "logs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> createLog(@RequestBody RequestLogger request) {
        return apiHashServices.createLog(request);
    }

    @GetMapping(value = "logs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ResponseLogger>> getAllLogs() {
        return apiHashServices.findAllLoggers();
    }

    @GetMapping(value = "logs/{hashtag}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ResponseLogger>> getLogByHashstag(@PathVariable("hashtag") String hashtag) {
        return apiHashServices.findByHashtag(hashtag);
    }

    @GetMapping(value = "logs/by/{logId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseLogger> getLogById(@PathVariable("logId") Integer logId) {
        return apiHashServices.findByLogId(logId);
    }

    @PutMapping(value = "hastags", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> updateHashstag(@RequestBody RequestHashtag requestHashtag) {
        return apiHashServices.update(requestHashtag);
    }
}
