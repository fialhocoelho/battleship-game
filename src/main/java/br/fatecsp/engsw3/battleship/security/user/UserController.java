package br.fatecsp.engsw3.battleship.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @ResponseBody
    @PostMapping
    public ResponseEntity addUser(@RequestBody User user) {
        if (user.getId() == 0) {
            repository.save(user);
            return ResponseEntity.ok("User created!");
        } else {
            return ResponseEntity.badRequest().body("Não informar ID, será gerado automáticamente!");
        }
    }

    @ResponseBody
    @GetMapping
    public Iterable getAllUsers() {
        return repository.findAll();
    }

    @ResponseBody
    @GetMapping(path = "{id}")
    public ResponseEntity getUser(@PathVariable int id) {
        User foundUser = repository.findOne(id);
        if (foundUser != null) {
            return ResponseEntity.ok(foundUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @PutMapping(path = "{id}")
    public ResponseEntity changeUser(@PathVariable int id, @RequestBody User user) {
        if (id == user.getId()) {
            if (repository.findOne(id) != null) {
                repository.save(user);
                return ResponseEntity.ok("User modified!");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().body("Id do user diferente da request!");
        }
    }

    @ResponseBody
    @DeleteMapping(path = "{id}")
    public ResponseEntity removeUser(@PathVariable int id) {
        User foundUser = repository.findOne(id);
        if (foundUser != null) {
            repository.delete(id);
            return ResponseEntity.ok("User removed!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
