package ma.enset.gestionetudiant.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
    @RequestMapping(value = "/login")
    public String login(){
        return "auth-login-basic";
    }
    @GetMapping("/403")
    public  String NotAutonticated(){
        return "403";
    }
}
