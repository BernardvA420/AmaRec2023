package com.springboot.registrationlogin.web;

//import com.impact.amarec.service.SongDao;
import com.springboot.registrationlogin.Model.Song;
import com.springboot.registrationlogin.Model.SongForm;
import com.springboot.registrationlogin.repository.SongRepository;
import com.springboot.registrationlogin.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.boot.web.servlet.error.ErrorController;

import java.util.List;

@Controller
public class AppController implements ErrorController{

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongService songService;


    // private final static String PATH = "/error";
    // //@Override
    // @RequestMapping(PATH)
    // @ResponseBody
    // public String getErrorPath() {
    //     //TODO Auto-generated method stub
    //     return "No Mapping Found";
    // }
    // @GetMapping({
    //         "/",
    //         "/startpage"
    // })
    // public String startpage(){
    //     return "startpage";
    // }


    @GetMapping("/selectGenreAndMood")
    public String showSongForm(Model model) {
        model.addAttribute("songForm", new SongForm());
        return "selectGenreAndMood";
    }

    @GetMapping({
            "/songs",
            "/songs/"
    })
    public String showAllSongs(Model model) {
        List<Song> songs = songService.getAllSongs();
        model.addAttribute("songs", songs);
        return "songs";
    }

    @GetMapping("/songs/filter")
    public String getSongsByGenreAndMood(@RequestParam("genre") String genre,
                                         @RequestParam("mood") String mood,
                                         Model model) {
        List<Song> songs = songService.getSongsByGenreAndMood(genre, mood);
        model.addAttribute("songs", songs);
        return "songs";
    }


    @GetMapping("/selectMood")
    public String selectMood(){
        return "selectMood";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }


}
