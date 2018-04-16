package br.fatecsp.engsw3.battleship.repository;

import org.springframework.data.repository.CrudRepository;

import br.fatecsp.engsw3.battleship.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String username);

}
