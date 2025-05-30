package com.aithinkers.GoldenGym.security;
import com.aithinkers.GoldenGym.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register", "/otp-verification", "/login","/home").permitAll()
               .requestMatchers("/home").hasAnyAuthority("ROLE_MEMBER","ROLE_TRAINER","ROLE_ADMIN")
                .requestMatchers("/members/**").hasAnyAuthority("ROLE_MEMBER","ROLE_ADMIN") // Ensure this is correct
               .requestMatchers("/trainers/**").hasAnyAuthority("ROLE_TRAINER","ROLE_ADMIN")
                .requestMatchers("/admins/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/authenticateTheUser")
                .defaultSuccessUrl("/home", true) // Redirect members correctly
                //.successHandler(new CustomLoginSuccessHandler())
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            // Uncomment if using API requests
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/access-denied")     
            );
        http.csrf(customizer ->customizer.disable());

        return http.build();
    }

  

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

  
    
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, CustomAuthenticationProvider authProvider) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authProvider)
                .build();
    }


 
}
