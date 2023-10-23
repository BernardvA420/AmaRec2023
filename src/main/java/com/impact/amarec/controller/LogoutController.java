package com.impact.amarec.controller;

import com.impact.amarec.entity.User;
import com.impact.amarec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

/*
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public LogoutController(UserRepository userRepository) {

        this.userRepository = userRepository;
    }
*/

    @GetMapping("/logout")
    public String logout() {
        // Perform the logout
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(null); // Clear the authentication
        }
        // Redirect to the login page or any other destination after logout
        return "redirect:/login";
    }


}

