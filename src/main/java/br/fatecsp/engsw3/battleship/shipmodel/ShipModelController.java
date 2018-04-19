package br.fatecsp.engsw3.battleship.shipmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/shipmodels")
public class ShipModelController {

    @Autowired
    private ShipModelRepository repository;

    @ResponseBody
    @PostMapping
    public ResponseEntity addShip(@RequestBody ShipModel shipModel) {
        if (shipModel.getId() == 0) {
            repository.save(shipModel);
            return ResponseEntity.ok("ShipModel created!");
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
        ShipModel naveEncontrada = repository.findOne(id);
        if (naveEncontrada != null) {
            return ResponseEntity.ok(naveEncontrada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @PutMapping(path = "{id}")
    public ResponseEntity changeShip(@PathVariable int id, @RequestBody ShipModel shipModel) {
        if (id == shipModel.getId()) {
            if (repository.findOne(id) != null) {
                repository.save(shipModel);
                return ResponseEntity.ok("ShipModel modified!");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().body("Id da shipmodel diferente da request!");
        }
    }

    @ResponseBody
    @DeleteMapping(path = "{id}")
    public ResponseEntity removeShip(@PathVariable int id) {
        ShipModel naveEncontrada = repository.findOne(id);
        if (naveEncontrada != null) {
            repository.delete(id);
            return ResponseEntity.ok("ShipModel removed");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
