package br.fatecsp.engsw3.battleship.nave;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Nave {

    @Id
    @GeneratedValue
    private int id;
    private String tipo;
    private int tamanho;

}
