package com.impact.amarec.controller;

import com.impact.amarec.Dto.SongCountDTO;
import com.impact.amarec.entity.Song;
import com.impact.amarec.entity.SongForm;
import com.impact.amarec.entity.User;
import com.impact.amarec.repository.SongRepository;
import com.impact.amarec.service.SongService;
import com.impact.amarec.service.UserInteractionService;
import com.impact.amarec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class SongController implements ErrorController {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserInteractionService userInteractionService;

    /*private final static String PATH = "/error";

    //@Override
    @RequestMapping(PATH)
    @ResponseBody
    public String getErrorPath() {
        //TODO Auto-generated method stub
        return "No Mapping Found";
    }*/

    @GetMapping("/selectGenreAndMood")
    public String showSongForm(Model model, Authentication authentication) {
        String email = null;
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the username from the authentication object
            email = authentication.getName();
            //System.out.println(email);

            // Retrieve user data from the database based on the username (you need to implement this)
            User user = userService.findUserByEmail(email);

            if (user != null) {
                // Add user data to the model
                model.addAttribute("user", user);
            }

        }
        model.addAttribute("songForm", new SongForm());
        return "selectGenreAndMood";
    }

    /*@GetMapping({
            "/songs",
            "/songs/"
    })
    public String showAllSongs(Model model) {
        List<Song> songs = songService.getAllSongs();
        model.addAttribute("songs", songs);
        return "songs";
    }*/

    @GetMapping({
            "/songs",
            "/songs/"
    })
    public String showAllSongs(Model model, Authentication authentication) {
        String email = null;
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the username from the authentication object
            email = authentication.getName();
            //System.out.println(email);

            // Retrieve user data from the database based on the username (you need to implement this)
            User user = userService.findUserByEmail(email);

            if (user != null) {
                // Add user data to the model
                model.addAttribute("user", user);
            }

        }

        // Use the userEmail to retrieve song counts for the logged-in user
        List<SongCountDTO> songCounts = userInteractionService.getSongCountsForUser(email);
        model.addAttribute("songCounts", songCounts);

        List<Song> songs = songService.getAllSongs();
        model.addAttribute("songs", songs);
        return "songs";
    }

    @GetMapping({
            "/my_favourites",
            "/my_favourites/"
    })
    public String showMyFavourites(Model model, Authentication authentication) {
        String email = null;
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the username from the authentication object
            email = authentication.getName();
            //System.out.println(email);

            // Retrieve user data from the database based on the username (you need to implement this)
            User user = userService.findUserByEmail(email);

            if (user != null) {
                // Add user data to the model
                model.addAttribute("user", user);
            }

        }

        // Use the userEmail to retrieve song counts for the logged-in user
        List<SongCountDTO> songCounts = userInteractionService.getSongCountsForUser(email);
        model.addAttribute("songCounts", songCounts);

        return "my_favourites";
    }

    @GetMapping({
            "/explore",
            "/explore/"
    })
    public String explorePage(Model model) {

        List<Song> songs = songService.getAllSongs();
        model.addAttribute("songs", songs);
        return "explore";
    }


    @GetMapping({
            "/moods",
            "/moods/"
    })
    public String showAllMoods(Model model, Authentication authentication) {
        String email = null;
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the username from the authentication object
            email = authentication.getName();
            //System.out.println(email);

            // Retrieve user data from the database based on the username (you need to implement this)
            User user = userService.findUserByEmail(email);

            if (user != null) {
                // Add user data to the model
                model.addAttribute("user", user);
            }

        }
        return "moods";
    }

    @GetMapping("/mood/{mood}")
    public String getSongsByMood(@PathVariable String mood, Model model, Authentication authentication) {

        String email = null;
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the username from the authentication object
            email = authentication.getName();
            //System.out.println(email);

            // Retrieve user data from the database based on the username (you need to implement this)
            User user = userService.findUserByEmail(email);

            if (user != null) {
                // Add user data to the model
                model.addAttribute("user", user);
            }

        }
        // Query the database to retrieve songs with the specified mood
        List<Song> songsWithMood = songRepository.findByMood(mood);

        // Add the list of songs to the model
        model.addAttribute("songs", songsWithMood);

        // Return the "songs" template
        return "songs";
    }

    @GetMapping({
            "/genres",
            "/genres/"
    })
    public String showAllGenres(Model model, Authentication authentication) {
        String email = null;
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the username from the authentication object
            email = authentication.getName();
            //System.out.println(email);

            // Retrieve user data from the database based on the username (you need to implement this)
            User user = userService.findUserByEmail(email);

            if (user != null) {
                // Add user data to the model
                model.addAttribute("user", user);
            }

        }
        return "genres";
    }

    @GetMapping("/genre/{genre}")
    public String getSongsByGenre(@PathVariable String genre, Model model, Authentication authentication) {

        String email = null;
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the username from the authentication object
            email = authentication.getName();
            //System.out.println(email);

            // Retrieve user data from the database based on the username (you need to implement this)
            User user = userService.findUserByEmail(email);

            if (user != null) {
                // Add user data to the model
                model.addAttribute("user", user);
            }

        }
        // Query the database to retrieve songs with the specified mood
        List<Song> songsWithGenre = songRepository.findByGenre(genre);

        // Add the list of songs to the model
        model.addAttribute("songs", songsWithGenre);

        // Return the "songs" template
        return "songs";
    }

    @GetMapping("/songs/filter")
    public String getSongsByGenreAndMood(@RequestParam("genre") String genre,
                                         @RequestParam("mood") String mood,
                                         Model model, Authentication authentication) {
        String email = null;
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the username from the authentication object
            email = authentication.getName();
            //System.out.println(email);

            // Retrieve user data from the database based on the username (you need to implement this)
            User user = userService.findUserByEmail(email);

            if (user != null) {
                // Add user data to the model
                model.addAttribute("user", user);
            }

        }
        List<Song> songs = songService.getSongsByGenreAndMood(genre, mood);
        model.addAttribute("songs", songs);
        return "songs";
    }
}
