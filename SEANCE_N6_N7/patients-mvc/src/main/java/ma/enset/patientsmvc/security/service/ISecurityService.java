package ma.enset.patientsmvc.security.service;

import ma.enset.patientsmvc.security.entities.AppRole;
import ma.enset.patientsmvc.security.entities.AppUser;

public interface ISecurityService {
    AppUser saveNewUser(String username, String password, String rePassword);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
}