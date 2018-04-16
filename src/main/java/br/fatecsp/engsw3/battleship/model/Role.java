package br.fatecsp.engsw3.battleship.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Role implements GrantedAuthority {

	@Id
	private int id;
	
	@NotNull
	private String role;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.role;
	}

}
