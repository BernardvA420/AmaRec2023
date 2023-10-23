package com.impact.amarec.controller;

//import com.impact.amarec.service.SongDao;
import com.impact.amarec.Dto.SongCountDTO;
import com.impact.amarec.repository.SongRepository;
import com.impact.amarec.repository.UserInteractionRepository;
import com.impact.amarec.service.SongService;
import com.impact.amarec.service.UserInteractionService;
import com.impact.amarec.entity.UserInteraction;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.boot.web.servlet.error.ErrorController;

import java.util.List;

@Controller
public class AppController implements ErrorController {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongService songService;

    @Autowired
    private UserInteractionRepository userInteractionRepository;

    @Autowired
    private UserInteractionService userInteractionService;

    @Autowired
    public AppController(UserInteractionService userInteractionService) {
        this.userInteractionService = userInteractionService;
    }



    private final static String PATH = "/error";

    //@Override
    @RequestMapping(PATH)
    @ResponseBody
    public String getErrorPath() {
        //TODO Auto-generated method stub
        return "No Mapping Found";
    }

    /*@GetMapping({
            "",
            "/",
            "/startpage"
    })
    public String startpage() {
        return "startpage";
    }*/

    /*@GetMapping("/home")
    public String home(Model model) {
        List<SongCountDTO> songCounts = userInteractionService.getSongCounts();
        model.addAttribute("songCounts", songCounts);
        return "home";
    }*/

    /*@GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the username (email) from the authentication object
            String userEmail = authentication.getName();

            // Use the userEmail to retrieve song counts for the logged-in user
            List<SongCountDTO> songCounts = userInteractionService.getSongCountsForUser(userEmail);

            model.addAttribute("songCounts", songCounts);
        }

        return "home";
    }*/



    /*@GetMapping("/index")
    public String index() {
        return "/index";
    }*/


    /*@GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }*/


}



