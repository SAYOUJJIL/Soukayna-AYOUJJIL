package com.example.authservice;

import com.example.authservice.sec.entities.AppRole;
import com.example.authservice.sec.entities.AppUser;
import com.example.authservice.sec.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
    //au démarrage créer un objet PasswordEncoder
    @Bean
    PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner start(AccountService accountService){
        return args -> {
            accountService.addNewRole(new AppRole(null,"USER"));
            accountService.addNewRole(new AppRole(null,"ADMIN"));

            accountService.addNewUser(new AppUser(null,"Soukayna","1234",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"Hayat","1234",new ArrayList<>()));

            accountService.addRoleToUser("Soukayna","USER");
            accountService.addRoleToUser("Soukayna","ADMIN");
            accountService.addRoleToUser("Hayat","USER");
        };
    }
}
