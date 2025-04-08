package com.aithinkers.GoldenGym.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aithinkers.GoldenGym.entity.Authority;
import com.aithinkers.GoldenGym.entity.User;
import com.aithinkers.GoldenGym.service.AuthorityService;
import com.aithinkers.GoldenGym.service.OtpService;
import com.aithinkers.GoldenGym.service.UserService;
import com.aithinkers.GoldenGym.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller

public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final OtpService otpService;
    private final AuthorityService authorityService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, OtpService otpService, AuthorityService authorityService) {
        this.userServiceImpl = userServiceImpl;
        this.otpService = otpService;
        this.authorityService = authorityService;
        
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name, 
                               @RequestParam String email,
                               @RequestParam String mobile,
                               @RequestParam String password,
                               @RequestParam String role) {

        // Create User
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setPassword(password);
        user.setVerified(false); // Initially not verified

        // Save user
        userServiceImpl.registerUser(user);

        // Assign Role in Authority table
        Authority authority = new Authority();
        authority.setUser(user);
        authority.setRole(role);  // ROLE_MEMBER, ROLE_TRAINER, ROLE_ADMIN

        authorityService.saveAuthority(authority);
        
        // Send OTP to user after registration**
        otpService.createOtp(user);

        return "redirect:/otp-verification?mobile=" + user.getMobile();
    }
    
    

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") long theId,
									Model theModel) {

		// get the member from the service
		User theUser = userServiceImpl.findById(theId);

		// set member as a model attribute to pre-populate the form
		theModel.addAttribute("user", theUser);

		// send over to our form
		return "admin-dashboard";
	}
	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User theUser) {

		// save the member
		userServiceImpl.save(theUser);

		// use a redirect to prevent duplicate submissions
		return "redirect:/admins";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("userId") long theId) {

		// delete the member
		userServiceImpl.deleteById(theId);

		// redirect to members
		return "redirect:/admins";

	}
	
	 @GetMapping("/admins")
    public String showAdmin(Model model) {
        List<User> admins = userServiceImpl.getAll();
        model.addAttribute("users", admins);
        return "admin-dashboard";
    }
    

    @GetMapping("/otp-verification")
    public String showOtpVerificationForm(@RequestParam String mobile, Model model) {
        model.addAttribute("mobile", mobile);
        return "otp-verification";
    }

    @PostMapping("/otp-verification")
    public String verifyOtp(@RequestParam String mobile, @RequestParam String otp) {
        Optional<User> user = userServiceImpl.findByMobile(mobile);
        if (user.isPresent() && otpService.verifyOtp(user.get(), otp)) {
            userServiceImpl.verifyUser (user.get()); // Mark user as verified
            return "redirect:/login"; // Redirect to login page after successful verification
        }
        return "otp-verification"; // Return to OTP verification if failed
    }

   
}
