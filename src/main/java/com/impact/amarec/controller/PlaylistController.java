package com.impact.amarec.controller;

import com.impact.amarec.entity.Playlist;
import com.impact.amarec.entity.Song;
import com.impact.amarec.entity.User;
import com.impact.amarec.service.PlaylistService;
import com.impact.amarec.service.SongService;
import com.impact.amarec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/playlists")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private SongService songService;

    @Autowired
    UserService userService;

    // Get all playlists
    @GetMapping("/playlists")
    public String getAllPlaylists(Model model, Authentication authentication) {
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
        List<Playlist> playlists = playlistService.getAllPlaylists();
        model.addAttribute("playlists", playlists);
        for (Playlist playlist : playlists) {
            System.out.println("Playlist Name: " + playlist.getName());
        }
        return "playlists";
    }

    @PostMapping("/playlists/create")
    public String createPlaylist(@RequestParam("name") String playlistName, Model model) {
        Playlist newPlaylist = new Playlist();
        newPlaylist.setName(playlistName);
        newPlaylist = playlistService.createPlaylist(newPlaylist);

        if (newPlaylist != null) {
            model.addAttribute("playlistId", newPlaylist.getId());
            return "redirect:/playlists";
        } else {
            return "playlists";
        }
    }


    @GetMapping("/playlists/{playlistId}/edit")
    public String editPlaylistForm(@PathVariable Long playlistId, Model model, Authentication authentication) throws Exception {
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
        // Provide data needed for editing the playlist, e.g., current name
        Playlist playlist = playlistService.getPlaylistById(playlistId);
        if (playlist != null) {
            model.addAttribute("playlist", playlist);
            return "edit-playlist";
        } else {
            return "playlist-not-found";
        }
    }

    @PostMapping("/playlists/{playlistId}/edit")
    public String editPlaylist(@PathVariable Long playlistId, @RequestParam("newName") String newName) throws Exception {
        Playlist playlist = playlistService.getPlaylistById(playlistId);
        if (playlist != null) {
            playlist.setName(newName);
            playlist = playlistService.updatePlaylist(playlist);
            if (playlist != null) {
                return "redirect:/playlists";
            }
        }
        return "edit-failed";
    }

    @PostMapping("/playlists/{playlistId}/delete")
    public String deletePlaylist(@PathVariable Long playlistId, Model model) {
        try {
            Playlist playlist = playlistService.getPlaylistById(playlistId);

            if (playlist == null) {
                // If the playlist is not found, you can redirect to an error page
                return "redirect:/playlist-not-found";
            }

            List<Song> songs = songService.getSongsByPlaylist(playlist);

            for (Song song : songs) {
                songService.deleteSong(song.getId());
            }

            playlistService.deletePlaylist(playlistId);

            // Add a success message to the model
            model.addAttribute("successMessage", "Playlist deleted successfully");

            // Redirect to the playlist page
            return "redirect:/playlists";
        } catch (Exception e) {
            // Handle any errors
            return "error-page"; // You can create a custom error page
        }
    }


    @GetMapping("/playlists/{playlistId}")
    public String viewPlaylist(@PathVariable Long playlistId, Model model, Authentication authentication) throws Exception {
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
        Playlist playlist = playlistService.getPlaylistById(playlistId);
        if (playlist != null) {
            model.addAttribute("playlist", playlist);
            List<Song> songs = playlist.getSongs();
            model.addAttribute("songs", songs);
            return "playlist-details";
        } else {
            return "playlist-not-found";
        }
    }


    // Add a song to a playlist
    @PostMapping("/playlists/{playlistName}/addSong")
    public ResponseEntity<String> addSongToPlaylist(@PathVariable String playlistName, @RequestParam Long songId) throws Exception {
        Playlist playlist = playlistService.getPlaylistByName(playlistName);
        if (playlist == null) {
            playlist = new Playlist();
            playlist.setName(playlistName);
            playlist = playlistService.createPlaylist(playlist);
        }

        Song song = songService.getSongById(songId);

        if (playlist != null && song != null) {
            playlist = playlistService.addSongToPlaylist(playlist.getId(), song);
            if (playlist != null) {
                return ResponseEntity.ok("Song added to the playlist successfully.");
            } else {
                return ResponseEntity.badRequest().body("Failed to add the song to the playlist.");
            }
        } else {
            return ResponseEntity.badRequest().body("Playlist or song not found.");
        }
    }

    // Add a song to the "Liked Songs" playlist
    @PostMapping("/playlists/addToLikedSongs")
    public ResponseEntity<String> addToLikedSongs(@RequestParam Long songId) throws Exception {
        Playlist likedSongsPlaylist = playlistService.getPlaylistByName("Liked Songs");

        Song song = songService.getSongById(songId);

        if (likedSongsPlaylist != null && song != null) {
            likedSongsPlaylist = playlistService.addSongToPlaylist(likedSongsPlaylist.getId(), song);

            if (likedSongsPlaylist != null) {
                return ResponseEntity.ok("Song liked and added to 'Liked Songs' playlist.");
            } else {
                return ResponseEntity.badRequest().body("Failed to like the song.");
            }
        } else {
            return ResponseEntity.badRequest().body("Playlist or song not found.");
        }
    }
    /*@GetMapping("/playlists/{playlistName}")
     public String viewPlaylist(@PathVariable String playlistName, Model model) throws Exception {
     Playlist playlist = playlistService.getPlaylistByName(playlistName);
     if (playlist != null) {
     model.addAttribute("playlist", playlist);
     List<Song> songs = playlist.getSongs();
     model.addAttribute("songs", songs);
     return "playlist-details";
     } else {
     return "playlist-not-found";
     }
     }*/
}