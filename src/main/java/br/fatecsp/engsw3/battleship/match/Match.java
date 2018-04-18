package br.fatecsp.engsw3.battleship.match;

import br.fatecsp.engsw3.battleship.scenery.Scenery;
import br.fatecsp.engsw3.battleship.ship.Ship;
import br.fatecsp.engsw3.battleship.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "matches") // MySQL não permite table com o nome match
public class Match {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Scenery scenery;

    @ManyToOne
    private User user1;

    @ManyToOne
    private User user2;

    @OneToMany
    private List<Ship> ships;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean[][] shots1;
    private boolean[][] shots2;
    private int nextUser;
    private int winner;

}
