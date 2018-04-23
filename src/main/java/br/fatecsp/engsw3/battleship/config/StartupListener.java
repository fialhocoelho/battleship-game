package br.fatecsp.engsw3.battleship.config;

import br.fatecsp.engsw3.battleship.security.role.Role;
import br.fatecsp.engsw3.battleship.security.user.User;
import br.fatecsp.engsw3.battleship.security.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createAdminUserIfNotExists();
        createDefaultUserIfNotExists();
    }

    private void createAdminUserIfNotExists() {
        User adminUser = userRepository.findByUsername("admin");
        if (adminUser == null) {
            adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(new BCryptPasswordEncoder().encode("admin"));
            Role admRole = new Role();
            admRole.setRole("ADMIN");
            adminUser.setRoles(Collections.singletonList(admRole));
            userRepository.save(adminUser);
            log.info("##### Admin user created. #####");
        } else {
            log.info("##### Admin user already exists. #####");
        }
    }

    private void createDefaultUserIfNotExists() {
        User user = userRepository.findByUsername("user");
        if (user == null) {
            user = new User();
            user.setUsername("user");
            user.setPassword(new BCryptPasswordEncoder().encode("user"));
            userRepository.save(user);
            log.info("##### Default user created. #####");
        } else {
            log.info("##### Default user already exists. #####");
        }
    }

}
