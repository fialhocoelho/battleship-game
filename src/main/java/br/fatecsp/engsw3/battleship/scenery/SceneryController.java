package br.fatecsp.engsw3.battleship.scenery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/sceneries")
public class SceneryController {

    @Autowired
    private SceneryRepository repository;

    @ResponseBody
    @PostMapping
    public ResponseEntity addScenery(@RequestBody Scenery scenery) {
        if (scenery.getId() == 0) {
            repository.save(scenery);
            return ResponseEntity.ok("Scenery created!");
        } else {
            return ResponseEntity.badRequest().body("Não informar ID, será gerado automáticamente!");
        }
    }

    @ResponseBody
    @GetMapping
    public Iterable getAllSceneries() {
        return repository.findAll();
    }

    @ResponseBody
    @GetMapping(path = "{id}")
    public ResponseEntity getScenery(@PathVariable int id) {
        Scenery foundScenery = repository.findOne(id);
        if (foundScenery != null) {
            return ResponseEntity.ok(foundScenery);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @PutMapping(path = "{id}")
    public ResponseEntity changeScenery(@PathVariable int id, @RequestBody Scenery scenery) {
        if (id == scenery.getId()) {
            if (repository.findOne(id) != null) {
                repository.save(scenery);
                return ResponseEntity.ok("Scenery modified!");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().body("Id do scenery diferente da request!");
        }
    }

    @ResponseBody
    @DeleteMapping(path = "{id}")
    public ResponseEntity removeScenery(@PathVariable int id) {
        Scenery foundScenery = repository.findOne(id);
        if (foundScenery != null) {
            repository.delete(id);
            return ResponseEntity.ok("Scenery removed!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
