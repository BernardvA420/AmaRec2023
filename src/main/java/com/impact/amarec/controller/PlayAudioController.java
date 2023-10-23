package com.impact.amarec.controller;

import com.impact.amarec.entity.Song;
import com.impact.amarec.entity.User;
import com.impact.amarec.service.SongService;
import com.impact.amarec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PlayAudioController {

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;


    @GetMapping("/audioPlayer")
    public String audioPlayer(@RequestParam("filePath") String googleDriveId, Model model, Authentication authentication) {
        Song song = songService.findByFilePath(googleDriveId);

        if (song != null) {
            if (authentication != null && authentication.isAuthenticated()) {
                // User is authenticated, provide full audio player
                String email = authentication.getName();
                // Retrieve user data from the database based on the email (you need to implement this)

                //System.out.println("Print this: " + email);
                User user = userService.findUserByEmail(email);

                if (user != null) {
                    // Add user data to the model
                    model.addAttribute("user", user);
                }

                model.addAttribute("song", song);
                return "audioPlayer"; // This should match your Thymeleaf template name
            } else {
                // User is not authenticated, provide 10-second preview
                model.addAttribute("song", song);
                return "audioPlayer-preview"; // Thymeleaf template for the preview
            }
        } else {
            // Handle the case where the song is not found
            return "redirect:/notfound"; // Redirect to a not found page or handle it accordingly
        }
    }


}

