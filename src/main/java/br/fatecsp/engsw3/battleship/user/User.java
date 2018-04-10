package br.fatecsp.engsw3.battleship.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String login;
    private String password;

}
