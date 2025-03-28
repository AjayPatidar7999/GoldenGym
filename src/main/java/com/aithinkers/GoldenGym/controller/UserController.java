package com.aithinkers.GoldenGym.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aithinkers.GoldenGym.entity.Authority;
import com.aithinkers.GoldenGym.entity.User;
import com.aithinkers.GoldenGym.service.AuthorityService;
import com.aithinkers.GoldenGym.service.OtpService;
import com.aithinkers.GoldenGym.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;
    private final OtpService otpService;
    private final AuthorityService authorityService;

    @Autowired
    public UserController(UserService userService, OtpService otpService, AuthorityService authorityService) {
        this.userService = userService;
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
        userService.registerUser(user);

        // Assign Role in Authority table
        Authority authority = new Authority();
        authority.setUser(user);
        authority.setRole(role);  // ROLE_MEMBER, ROLE_TRAINER, ROLE_ADMIN

        authorityService.saveAuthority(authority);
        
        // ✅ **Send OTP to user after registration**
        otpService.createOtp(user);

        return "redirect:/otp-verification?mobile=" + user.getMobile();
    }
    
    
    @GetMapping("/members")
    public String showMembers(Model model) {
        List<User> members = userService.getUsersByRole("ROLE_MEMBER");
        model.addAttribute("users", members);
        return "members";
    }

    @GetMapping("/trainers")
    public String showTrainers(Model model) {
        List<User> trainers = userService.getUsersByRole("ROLE_TRAINER");
        model.addAttribute("users", trainers);
        return "trainers";
    }
    

    @GetMapping("/otp-verification")
    public String showOtpVerificationForm(@RequestParam String mobile, Model model) {
        model.addAttribute("mobile", mobile);
        return "otp-verification";
    }

    @PostMapping("/otp-verification")
    public String verifyOtp(@RequestParam String mobile, @RequestParam String otp) {
        Optional<User> user = userService.findByMobile(mobile);
        if (user.isPresent() && otpService.verifyOtp(user.get(), otp)) {
            userService.verifyUser (user.get()); // Mark user as verified
            return "redirect:/login"; // Redirect to login page after successful verification
        }
        return "otp-verification"; // Return to OTP verification if failed
    }

   
}
