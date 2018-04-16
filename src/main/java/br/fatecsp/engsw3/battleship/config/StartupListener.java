package br.fatecsp.engsw3.battleship.config;

import br.fatecsp.engsw3.battleship.model.Role;
import br.fatecsp.engsw3.battleship.model.User;
import br.fatecsp.engsw3.battleship.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("###### onApplicationEvent ######");
        createAdminUserIfNotExists();
    }

    private void createAdminUserIfNotExists() {
        User admin = userRepository.findByUsername("admin");
        if (admin == null) {
            admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin");
            Role admRole = new Role();
            admRole.setRole("ADMIN");
            admin.setRoles(Collections.singletonList(admRole));
            userRepository.save(admin);
            log.info("Admin user created.");
        } else {
            log.info("Admin user already exists.");
        }
    }

}
