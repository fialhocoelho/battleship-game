package br.fatecsp.engsw3.battleship.ship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ShipController {

    @Autowired
    private ShipRepository repository;

    public void addShip(Ship ship) {
        repository.save(ship);
    }

    public Iterable<Ship> getAllShips() {
        return repository.findAll();
    }

    public Ship getShip(int id) {
        return repository.findOne(id);
    }

    public void changeShip(Ship ship) {
        repository.save(ship);
    }

    public void removeShip(Ship ship) {
        repository.delete(ship);
    }

}
