package br.fatecsp.engsw3.battleship.match;

import br.fatecsp.engsw3.battleship.scenery.Scenery;
import br.fatecsp.engsw3.battleship.ship.Ship;
import br.fatecsp.engsw3.battleship.user.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Match {

    @Id
    @GeneratedValue
    private int id;
    private Scenery scenery;
    private User user1;
    private User user2;
    private List<Ship> ships;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean[][] shots1;
    private boolean[][] shots2;
    private int nextUser;
    private int winner;

}
