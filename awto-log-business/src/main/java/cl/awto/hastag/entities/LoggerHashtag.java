package cl.awto.hastag.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "awlog_logger_hashtag")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class LoggerHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "log_id", nullable = false)
    private Logger logger;

    @ManyToOne
    @JoinColumn(name = "hastag_id", nullable = false)
    private Hashtag hashtag;
}
