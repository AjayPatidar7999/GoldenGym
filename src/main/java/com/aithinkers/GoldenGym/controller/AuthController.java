package com.aithinkers.GoldenGym.controller;





import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.aithinkers.GoldenGym.entity.User;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; 
    }
    
    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";
    }

    //HOME PAGE
    
    @GetMapping("/home")
    public String showHomePage() {
       
        return "home/home";
    }
    

    @GetMapping("/contact")
    public String showContactPage() {
        return "home/contact";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "home/about";
    }

    @GetMapping("/career")
    public String showCareerPage() {
        return "home/career";
    }

    @GetMapping("/help")
    public String showHelpPage() {
        return "home/help";
    }
 
    
}
