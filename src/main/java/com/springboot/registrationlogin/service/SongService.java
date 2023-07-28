package com.springboot.registrationlogin.service;

import com.springboot.registrationlogin.Model.Song;
import com.springboot.registrationlogin.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

