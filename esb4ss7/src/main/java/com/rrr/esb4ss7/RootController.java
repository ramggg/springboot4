package com.rrr.esb4ss7;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }
}

