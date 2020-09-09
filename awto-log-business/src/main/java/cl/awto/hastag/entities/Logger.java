package cl.awto.hastag.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "awlog_logger")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Logger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "creation_date", nullable = false)
    private Instant creation_date;

    @Column(name = "host", nullable = false)
    private String host;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "stacktrace", nullable = false)
    private String stacktrace;
}
