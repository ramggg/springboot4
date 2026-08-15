package com.rrr.esb4ss7;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/r")
public class RController {

    private final About about;

    public RController(About about) {
        this.about = about;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from " + about.getName();
    }
}
