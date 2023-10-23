package com.impact.amarec.controller;

import com.impact.amarec.entity.User;
import com.impact.amarec.entity.UserInteraction;
import com.impact.amarec.repository.UserInteractionRepository;
import com.impact.amarec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Controller
public class UserInteractionController {

    @Autowired
    private UserInteractionRepository userInteractionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public UserInteractionController(UserInteractionRepository userInteractionRepository) {
        this.userInteractionRepository = userInteractionRepository;
    }
    @PostMapping("/track-interaction")
    public void trackInteraction(@RequestBody UserInteraction userInteraction) {
        // Save the interaction data to the database using the UserInteractionRepository
        userInteraction.setTimestamp(new Date());
        userInteractionRepository.save(userInteraction);
    }




}

