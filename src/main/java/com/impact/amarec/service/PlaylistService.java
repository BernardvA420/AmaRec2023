package com.impact.amarec.service;

import com.impact.amarec.entity.Playlist;
import com.impact.amarec.entity.Song;
import com.impact.amarec.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    public Playlist createPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public Playlist addSongToPlaylist(Long playlistId, Song song) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        song.setPlaylist(playlist);
        playlist.getSongs().add(song);

        return playlistRepository.save(playlist);
    }

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public Playlist getPlaylistById(Long playlistId) {
        return playlistRepository.findById(playlistId).orElse(null);
    }

    public Playlist getPlaylistByName(String playlistName) throws Exception {
        Playlist playlist = playlistRepository.findByName(playlistName);

        if (playlist != null) {
            return playlist;
        } else {
            // Handle the case where no playlist with the given name is found
            throw new Exception("Playlist with name " + playlistName + " not found");
        }
    }

    public Playlist updatePlaylist(Playlist playlist) {
        // You can add validation and error handling here if needed
        return playlistRepository.save(playlist);
    }

    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }
}