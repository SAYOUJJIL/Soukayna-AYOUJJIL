package ma.enset.gestionetudiant.security.service;

import ma.enset.gestionetudiant.security.entities.AppRole;
import ma.enset.gestionetudiant.security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username , String password , String verifyPassword);
    AppRole savaNewRole(String roleName, String description);
    void addRoleToUser(String  username,String roleName);
    void removeRoleToUser(String  username,String roleName);
    AppUser loadUserByUsername(String username);

}
