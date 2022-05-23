package ma.enset.gestionetudiant.security.service;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import ma.enset.gestionetudiant.security.entities.AppRole;
import ma.enset.gestionetudiant.security.entities.AppUser;
import ma.enset.gestionetudiant.security.repositores.AppRoleRepository;
import ma.enset.gestionetudiant.security.repositores.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@Transactional
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser saveNewUser(String username, String password, String verifyPassword) {
        if(!password.equals(verifyPassword)) throw new RuntimeException("Passwords not match");
        String hashedPassword=passwordEncoder.encode(password);
        AppUser appUser=new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPassword);
        appUser.setActive(true);
        AppUser savedAppUser=appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole savaNewRole(String roleName, String description) {
        AppRole appRole=appRoleRepository.findByRole(roleName);
        if (appRole!=null) throw new RuntimeException(roleName+"Allready exist");
        appRole =new AppRole();
        appRole.setRole(roleName);
        appRole.setDescription(description);
        AppRole savedApprole= appRoleRepository.save(appRole);
        return savedApprole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser==null) throw new RuntimeException(username+"not  exist");
        AppRole appRole=appRoleRepository.findByRole(roleName);
        if (appRole==null) throw new RuntimeException(roleName+"not  exist");
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public void removeRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser==null) throw new RuntimeException(username+"not  exist");

        AppRole appRole=appRoleRepository.findByRole(roleName);
        if (appRole==null) throw new RuntimeException(roleName+"not  exist");

        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        AppUser appUser=new AppUser();
        appUser= appUserRepository.findByUsername(username);
        return appUser;
    }
}
