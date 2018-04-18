package br.fatecsp.engsw3.battleship.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/matches")
public class MatchController {

    @Autowired
    private MatchRepository repository;

    @ResponseBody
    @PostMapping
    public ResponseEntity addMatch(@RequestBody Match match) {
        if (match.getId() == 0) {
            repository.save(match);
            return ResponseEntity.ok("Match created!");
        } else {
            return ResponseEntity.badRequest().body("Não informar ID, será gerado automáticamente!");
        }
    }

    @ResponseBody
    @GetMapping
    public Iterable getAllMatches() {
        return repository.findAll();
    }

    @ResponseBody
    @GetMapping(path = "{id}")
    public ResponseEntity getMatch(@PathVariable int id) {
        Optional<Match> foundMatch = repository.findById(id);
        if (foundMatch.isPresent()) {
            return ResponseEntity.ok(foundMatch.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @PutMapping(path = "{id}")
    public ResponseEntity changeMatch(@PathVariable int id, @RequestBody Match match) {
        if (id == match.getId()) {
            if (repository.findById(id).isPresent()) {
                repository.save(match);
                return ResponseEntity.ok("Match modified!");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().body("Id do match diferente da request!");
        }
    }

    @ResponseBody
    @DeleteMapping(path = "{id}")
    public ResponseEntity removeMatch(@PathVariable int id) {
        Optional<Match> foundMatch = repository.findById(id);
        if (foundMatch.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok("Match removed!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
