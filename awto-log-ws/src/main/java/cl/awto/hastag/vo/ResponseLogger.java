package cl.awto.hastag.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseLogger {
    private Integer id;
    private String host;
    private String origin;
    private String details;
    private String stacktrace;
    private List<String> hashtags;


}
