package com.rrr.esb4ss7;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import jakarta.servlet.http.HttpSession;
import java.util.Collections;

@Controller
public class LoginController {

    // Hardcoded credentials
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "password";

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {

        // Validate hardcoded credentials
        if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
            // Store user info in session
            session.setAttribute("username", username);

            // Create and set Authentication in Spring Security context
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    password,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
            );

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            // Also store in session for persistence across requests
            session.setAttribute("SPRING_SECURITY_CONTEXT", context);

            return "redirect:/home";
        } else {
            // Add error message to model
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}

