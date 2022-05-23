package ma.enset.gestionetudiant.security.repositores;

import ma.enset.gestionetudiant.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRole(String roleName);
}
