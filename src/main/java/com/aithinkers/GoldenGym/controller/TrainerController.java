package com.aithinkers.GoldenGym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.aithinkers.GoldenGym.entity.User;
import com.aithinkers.GoldenGym.service.UserService;
import com.aithinkers.GoldenGym.service.UserServiceImpl;

public class TrainerController {


    private final UserServiceImpl userServiceImpl;

    @Autowired
    public TrainerController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
       
        
    }
	
	//ADD MEMBERS CRUD ND UPDATE,DELETE,ADD BUTTONS TO ADMIN HTML REDIRECT THEM TO MEMBERCONTROLLER
	 @GetMapping("/trainers")
	    public String showTrainers(Model model) {
	        List<User> trainers = userServiceImpl.getUsersByRole("ROLE_TRAINER");
	        model.addAttribute("users", trainers);
	        return "trainer/trainers";
	    }
	
	 

	    @GetMapping("/contact")
	    public String showContactPage() {
	        return "trainer/contact";
	    }

	    @GetMapping("/about")
	    public String showAboutPage() {
	        return "trainer/about";
	    }

	    @GetMapping("/career")
	    public String showCareerPage() {
	        return "trainer/career";
	    }

	    @GetMapping("/help")
	    public String showHelpPage() {
	        return "trainer/help";
	    }
	 

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
