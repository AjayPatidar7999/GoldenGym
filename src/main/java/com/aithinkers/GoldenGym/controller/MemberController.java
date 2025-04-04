package com.aithinkers.GoldenGym.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aithinkers.GoldenGym.entity.TrainerAssignment;
import com.aithinkers.GoldenGym.entity.User;
import com.aithinkers.GoldenGym.repo.UserRepository;
import com.aithinkers.GoldenGym.service.AuthorityService;
import com.aithinkers.GoldenGym.service.OtpService;
import com.aithinkers.GoldenGym.service.TrainerAssignmentService;
import com.aithinkers.GoldenGym.service.UserService;
import com.aithinkers.GoldenGym.service.UserServiceImpl;

@Controller
public class MemberController {

    private final UserServiceImpl userServiceImpl;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainerAssignmentService trainerAssignmentService;

    @Autowired
    public MemberController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
       
        
    }
    
    
//not printing name of trainer on members page
    @GetMapping("/member/dashboard")
    public String memberDashboard(Model model, Principal principal) {
        String username = principal.getName();
        
        Optional<User> optionalMember = userRepository.findByEmail(username);

        if (optionalMember.isPresent()) {
            User member = optionalMember.get();

            // Get assigned trainer
            Optional<TrainerAssignment> assignmentOpt = trainerAssignmentService.findTrainerForMember(member);

            if (assignmentOpt.isPresent()) {
                User trainer = assignmentOpt.get().getTrainer();
                model.addAttribute("trainerName", trainer.getName());
                System.out.println("Logged in user: " + username);
                System.out.println("Member name: " + member.getName());
                System.out.println("Trainer: " + (assignmentOpt.isPresent() ? trainer.getName() : "Not Assigned"));

            } else {
                model.addAttribute("trainerName", "Not Assigned");
            }

            model.addAttribute("memberName", member.getName());
            
            return "member/members"; // Your member dashboard view
        } else {
            // If user is not found, show error or redirect
            model.addAttribute("errorMessage", "User not found");
            return "error/user-not-found"; // You can create this error page
        }
        
        
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	//ADD MEMBERS CRUD ND UPDATE,DELETE,ADD BUTTONS TO ADMIN HTML REDIRECT THEM TO MEMBERCONTROLLER
	 @GetMapping("/members")
	    public String showMembers(Model model) {
		 System.out.println("Inside /members controller"); // debug
	        List<User> members = userServiceImpl.getUsersByRole("ROLE_MEMBER");
	        model.addAttribute("users", members);
	        return "member/members";
	    }
	
	 

	    @GetMapping("/member/contact")
	    public String showContactPage() {
	        return "member/contact";
	    }

	    @GetMapping("/member/about")
	    public String showAboutPage() {
	        return "member/about";
	    }

	    @GetMapping("/member/career")
	    public String showCareerPage() {
	        return "member/career";
	    }

	    @GetMapping("/member/help")
	    public String showHelpPage() {
	        return "member/help";
	    }
	 

	
	
}
