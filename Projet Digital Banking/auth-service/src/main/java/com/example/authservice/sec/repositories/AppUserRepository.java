package com.example.authservice.sec.repositories;
import com.example.authservice.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
      AppUser findByUsername(String username);
}
