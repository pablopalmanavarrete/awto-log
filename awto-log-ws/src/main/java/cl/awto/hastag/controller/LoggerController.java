package cl.awto.hastag.controller;

import cl.awto.hastag.services.ApiHashServices;
import cl.awto.hastag.vo.RequestHashtag;
import cl.awto.hastag.vo.RequestLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public String getAllLogs() {
        return "ok getAllLogs";
    }

    @GetMapping(value = "logs/{hashtag}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getLogByHashstag(@PathVariable("hashtag") String hashtag) {
        return "ok getLogByHashstag" + hashtag;
    }

    @GetMapping(value = "logs/by/{logId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getLogById(@PathVariable("logId") Integer logId) {
        return "ok getLogById" + logId;
    }

    @PutMapping(value = "hastags", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> updateHashstag(@RequestBody RequestHashtag requestHashtag) {
        return apiHashServices.update(requestHashtag);
    }
}
