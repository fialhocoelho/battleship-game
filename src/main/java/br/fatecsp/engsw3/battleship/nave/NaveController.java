package br.fatecsp.engsw3.battleship.nave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/nave")
public class NaveController {

    @Autowired
    private NaveRepository naveRepository;

    @ResponseBody
    @PostMapping
    public ResponseEntity addNave(@RequestBody Nave nave) {
        if (nave.getId() == 0) {
            naveRepository.save(nave);
            return ResponseEntity.ok("Nave criada!");
        } else {
            return ResponseEntity.badRequest().body("Não informar ID, será gerado automáticamente!");
        }
    }

    @ResponseBody
    @GetMapping
    public Iterable getAllNaves() {
        return naveRepository.findAll();
    }

    @ResponseBody
    @GetMapping(path = "{id}")
    public ResponseEntity getNave(@PathVariable int id) {
        Optional<Nave> naveEncontrada = naveRepository.findById(id);
        if (naveEncontrada.isPresent()) {
            return ResponseEntity.ok(naveEncontrada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @PutMapping(path = "{id}")
    public ResponseEntity changeNave(@PathVariable int id, @RequestBody Nave nave) {
        if (id == nave.getId()) {
            if (naveRepository.findById(id).isPresent()) {
                naveRepository.save(nave);
                return ResponseEntity.ok("Nave modificada!");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().body("Id da nave diferente da request!");
        }
    }

    @ResponseBody
    @DeleteMapping(path = "{id}")
    public ResponseEntity removeNave(@PathVariable int id) {
        Optional<Nave> naveEncontrada = naveRepository.findById(id);
        if (naveEncontrada.isPresent()) {
            naveRepository.deleteById(id);
            return ResponseEntity.ok("Nave removida");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
