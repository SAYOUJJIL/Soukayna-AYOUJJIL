package ma.enset.jpaenset.service;

import lombok.AllArgsConstructor;
import ma.enset.jpaenset.entities.Role;
import ma.enset.jpaenset.entities.User;
import ma.enset.jpaenset.repositories.RoleRepository;
import ma.enset.jpaenset.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

//service indique que la classe doit etre instancier au demarrage service , transactional
@Service
@Transactional
@AllArgsConstructor
public class IUserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
       return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
          User user = findUserByUserName(userName);
            Role role = findRoleByRoleName(roleName);
            if(user.getRoles()!=null){
                user.getRoles().add(role);
                role.getUsers().add(user);
            }
            //userRepository.save(user);
    }

    @Override
    public User authentificate(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if (user==null) throw new RuntimeException("Bad credentials");
        if (user.getPassword().equals(password)){
            return user;
        }
        throw new RuntimeException("Bad credentials");
    }
}
