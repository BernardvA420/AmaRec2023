package com.impact.amarec.controller;

//import com.impact.amarec.Dto.UserDto;
import com.impact.amarec.entity.User;
import com.impact.amarec.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @RequestMapping(value = {"/login", "/"})
    public String loginOrSongsPage(HttpServletRequest request, HttpServletResponse response) {
        // Check if the user is authenticated
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/songs"; // Redirect to songs page if authenticated
        }

        return "login"; // Display the login page
    }






}

