package com.vgb.demo.secure;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public String hello() {
        return "hi";
    }

    @GetMapping("/bye")
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN')")
    public String goodbye() {
        return "goodbye";
    }

    @GetMapping("/console")
    public String console() {
        return "this is the console";
    }

    @GetMapping("/")
    public String start() {
        return "this is the start of the internet";
    }
}
