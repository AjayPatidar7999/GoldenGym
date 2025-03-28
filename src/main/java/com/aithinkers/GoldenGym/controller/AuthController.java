package com.aithinkers.GoldenGym.controller;





import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    
}
