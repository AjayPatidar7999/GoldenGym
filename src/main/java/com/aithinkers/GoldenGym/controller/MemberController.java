package com.aithinkers.GoldenGym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aithinkers.GoldenGym.entity.User;
import com.aithinkers.GoldenGym.service.AuthorityService;
import com.aithinkers.GoldenGym.service.OtpService;
import com.aithinkers.GoldenGym.service.UserService;
import com.aithinkers.GoldenGym.service.UserServiceImpl;

public class MemberController {

    private final UserServiceImpl userService;

    @Autowired
    public MemberController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
       
        
    }
	
	//ADD MEMBERS CRUD ND UPDATE,DELETE,ADD BUTTONS TO ADMIN HTML REDIRECT THEM TO MEMBERCONTROLLER
	 @GetMapping("/members")
	    public String showMembers(Model model) {
	        List<User> members = userService.getUsersByRole("ROLE_MEMBER");
	        model.addAttribute("users", members);
	        return "member/members";
	    }
	
	 

	    @GetMapping("/contact")
	    public String showContactPage() {
	        return "member/contact";
	    }

	    @GetMapping("/about")
	    public String showAboutPage() {
	        return "member/about";
	    }

	    @GetMapping("/career")
	    public String showCareerPage() {
	        return "member/career";
	    }

	    @GetMapping("/help")
	    public String showHelpPage() {
	        return "member/help";
	    }
	 

	
	
}
