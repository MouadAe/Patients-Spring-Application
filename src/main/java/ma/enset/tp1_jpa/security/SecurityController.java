package ma.enset.tp1_jpa.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SecurityController {
    @GetMapping("notAuthorized")
     public String error(){
        return "notAuthorized";
     }

    @GetMapping("/loginPage")
     public String login(){
        return "loginPage";
     }
    @GetMapping("/logout")
     public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/loginPage";
     }
}
