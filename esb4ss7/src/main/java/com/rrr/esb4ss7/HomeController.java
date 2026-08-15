package com.rrr.esb4ss7;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        // Get authentication from Spring Security
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Get username from session or authentication
        String username = (String) session.getAttribute("username");
        if (username == null && auth != null && auth.isAuthenticated()) {
            username = auth.getName();
        }

        // If no username found, redirect to login
        if (username == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", username);
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate session
        session.invalidate();

        // Clear Spring Security context
        SecurityContextHolder.clearContext();

        return "redirect:/login";
    }
}

