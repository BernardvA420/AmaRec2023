package com.impact.amarec.service;

import com.impact.amarec.entity.Playlist;
import com.impact.amarec.entity.Song;
import com.impact.amarec.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public List<Song> getSongsByGenreAndMood(String genre, String mood) {
        return songRepository.findByGenreAndMood(genre, mood);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song findByFilePath(String filePath) {
        return songRepository.findByFilePath(filePath);
    }

    public Song getSongById(Long songId) {
        Song song = songRepository.getById(songId);
        return song;
    }

    public List<Song> getSongsByPlaylist(Playlist playlist) {
        return songRepository.findByPlaylist(playlist);
    }

    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    public void deleteSong(Long songId) {
        songRepository.deleteById(songId);
    }
}
