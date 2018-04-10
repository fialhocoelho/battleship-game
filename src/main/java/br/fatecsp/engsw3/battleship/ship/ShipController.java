package br.fatecsp.engsw3.battleship.ship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/ships")
public class ShipController {

    @Autowired
    private ShipRepository repository;

    @ResponseBody
    @PostMapping
    public ResponseEntity addShip(@RequestBody Ship ship) {
        if (ship.getId() == 0) {
            repository.save(ship);
            return ResponseEntity.ok("Ship criada!");
        } else {
            return ResponseEntity.badRequest().body("Não informar ID, será gerado automáticamente!");
        }
    }

    @ResponseBody
    @GetMapping
    public Iterable getAllShips() {
        return repository.findAll();
    }

    @ResponseBody
    @GetMapping(path = "{id}")
    public ResponseEntity getShip(@PathVariable int id) {
        Optional<Ship> naveEncontrada = repository.findById(id);
        if (naveEncontrada.isPresent()) {
            return ResponseEntity.ok(naveEncontrada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @PutMapping(path = "{id}")
    public ResponseEntity changeShip(@PathVariable int id, @RequestBody Ship ship) {
        if (id == ship.getId()) {
            if (repository.findById(id).isPresent()) {
                repository.save(ship);
                return ResponseEntity.ok("Ship modificada!");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().body("Id da ship diferente da request!");
        }
    }

    @ResponseBody
    @DeleteMapping(path = "{id}")
    public ResponseEntity removeShip(@PathVariable int id) {
        Optional<Ship> naveEncontrada = repository.findById(id);
        if (naveEncontrada.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok("Ship removida");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
