package ma.enset.gestionetudiant.security.service;
import lombok.AllArgsConstructor;
import ma.enset.gestionetudiant.security.entities.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class UserDetaileServiceImpl implements UserDetailsService {
    private  SecurityService securityService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=securityService.loadUserByUsername(username);
        Collection<GrantedAuthority> authorities1=
                appUser.getAppRoles()
                        .stream()
                        .map(role->new SimpleGrantedAuthority(role.getRole()))
                        .collect(Collectors.toList());
        User user= new User(appUser.getUsername(),appUser.getPassword(),authorities1);
        return user;
    }
}