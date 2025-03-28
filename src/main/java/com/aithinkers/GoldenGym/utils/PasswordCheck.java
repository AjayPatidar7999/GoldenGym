package com.aithinkers.GoldenGym.utils;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordCheck {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // Hashed password from your DB
        String hashedPassword = "$2a$10$UrnNZ2cn8XZWlGWhc.bCJevWvM/EZlNHc7srn3UpkvMgTBUTVJE/i";
        
        // Checking if "ajay123" matches stored password
        System.out.println(encoder.matches("ajay123", hashedPassword));
    }
}

