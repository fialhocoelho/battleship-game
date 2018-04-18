package br.fatecsp.engsw3.battleship.security.role;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
        return role;
    }

}