package br.fatecsp.engsw3.battleship.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        Optional<User> foundUser = repository.findById(id);
        if (foundUser.isPresent()) {
            return ResponseEntity.ok(foundUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @PutMapping(path = "{id}")
    public ResponseEntity changeUser(@PathVariable int id, @RequestBody User user) {
        if (id == user.getId()) {
            if (repository.findById(id).isPresent()) {
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
        Optional<User> foundUser = repository.findById(id);
        if (foundUser.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok("User removed!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
