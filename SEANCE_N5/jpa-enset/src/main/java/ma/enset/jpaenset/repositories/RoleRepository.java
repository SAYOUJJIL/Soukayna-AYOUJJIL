package ma.enset.jpaenset.repositories;

import ma.enset.jpaenset.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
}
