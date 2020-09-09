package cl.awto.hastag.controller;

import cl.awto.hastag.vo.RequestHashtag;
import cl.awto.hastag.vo.RequestLogger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoggerController {


    @PostMapping(value = "logs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createLog(@RequestBody RequestLogger request) {
        return "ok createLog " + request.toString();
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
    public String updateHashstag(@RequestBody RequestHashtag requestHashtag) {
        return "ok updateHashstag" + requestHashtag.toString();
    }
}
