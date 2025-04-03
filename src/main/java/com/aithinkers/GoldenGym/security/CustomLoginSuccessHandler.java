package com.aithinkers.GoldenGym.security;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority(); // Get assigned role

            // Check role and redirect accordingly
            switch (role) {
                case "ROLE_MEMBER":
                    response.sendRedirect("/members");
                    return;
                case "ROLE_TRAINER":
                    response.sendRedirect("/trainers");
                    return;
                case "ROLE_ADMIN":
                    response.sendRedirect("admins");
                    return;
            }
        }

        // Default fallback redirect (if no role is found)
        response.sendRedirect("/access-denied");
    }
}