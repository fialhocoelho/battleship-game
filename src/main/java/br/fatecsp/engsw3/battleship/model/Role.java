package br.fatecsp.engsw3.battleship.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Role implements GrantedAuthority {

	@Id
    @GeneratedValue
	private int id;
	
	@NotNull
	private String role;

	@Override
	public String getAuthority() {
		return this.role;
	}

}
