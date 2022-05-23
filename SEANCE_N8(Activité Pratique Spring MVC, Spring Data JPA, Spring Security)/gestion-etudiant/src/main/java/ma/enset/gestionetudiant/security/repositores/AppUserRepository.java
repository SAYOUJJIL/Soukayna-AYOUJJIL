package ma.enset.gestionetudiant.security.repositores;

import ma.enset.gestionetudiant.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppUserRepository extends JpaRepository<AppUser,String> {
  AppUser findByUsername(String username);
}
