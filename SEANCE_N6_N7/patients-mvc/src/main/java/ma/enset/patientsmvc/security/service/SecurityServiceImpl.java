package ma.enset.patientsmvc.security.service;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;

import ma.enset.patientsmvc.security.entities.AppRole;
import ma.enset.patientsmvc.security.entities.AppUser;
import ma.enset.patientsmvc.security.repositories.AppRoleRepository;
import ma.enset.patientsmvc.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements ISecurityService
{
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if (!password.equals(rePassword)) throw new RuntimeException("Password not match");
        String hashedPWD = passwordEncoder.encode(password);

        AppUser appUser = new AppUser();
        appUser.setId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole!=null) throw new RuntimeException("Role "+roleName+ "Already exist");
        appRole = new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("User not found!!!");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("Role not found!!!");
        appUser.getAppRoles().add(appRole);
        appRole.getUsers().add(appUser);
        //appUserRepository.save(appUser); car on @Transactional

    }

    @Override
    public void removeRoleFromUser(String username, String roleName)
    {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("User not found!!!");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("Role not found!!!");
        appUser.getAppRoles().remove(appRole);
        appRole.getUsers().remove(appUser);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}