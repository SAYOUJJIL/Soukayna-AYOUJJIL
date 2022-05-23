package ma.enset.jpaenset.web;

import lombok.AllArgsConstructor;
import ma.enset.jpaenset.entities.User;
import ma.enset.jpaenset.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
@AllArgsConstructor
public class UserController {
    private IUserService iUserService;
    @GetMapping("/users/{username}")
    public User user(@PathVariable String username){
        User user=iUserService.findUserByUserName(username);
        return  user;
    }
}
