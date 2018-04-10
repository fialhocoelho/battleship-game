package br.fatecsp.engsw3.battleship.ship;

import br.fatecsp.engsw3.battleship.shipmodel.ShipModel;
import br.fatecsp.engsw3.battleship.user.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Ship {

    @Id
    @GeneratedValue
    private int id;
    private ShipModel model;
    private User user;
    private int startPositionLine;
    private int startPositionColumn;
    private int endPositionLine;
    private int endPositionColumn;

}
