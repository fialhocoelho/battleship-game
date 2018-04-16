package br.fatecsp.engsw3.battleship.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@ManyToMany
	@JoinTable(
			name = "user_role", //tabela a ser criada
			joinColumns = @JoinColumn(name="user_id"), //coluna a ser criada na nova tabela
			inverseJoinColumns = @JoinColumn(name="role_id")
			)
	private List<Role> roles;



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return (Collection<? extends GrantedAuthority>) this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
