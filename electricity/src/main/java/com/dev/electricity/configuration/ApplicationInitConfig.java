package com.dev.electricity.configuration;


import com.dev.electricity.entity.User;
import com.dev.electricity.enums.Role;
import com.dev.electricity.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
              if (userRepository.findByUsername("electrician").isEmpty()) {
                  var roles = new HashSet<String>();
                  roles.add(Role.ELECTRICIAN.name());

                  User user = User.builder()
                          .username("electrician")
                          .password(passwordEncoder.encode("electrician"))
                          .roles(roles)
                          .build();

                  userRepository.save(user);
                  log.warn("electrician user has been created with default password");
              }


        };
    }
}
