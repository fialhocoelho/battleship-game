package br.fatecsp.engsw3.battleship.scenery;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Scenery {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int qtyLines;
    private int qtyColumns;

}
