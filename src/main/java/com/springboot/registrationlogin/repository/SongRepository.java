package com.springboot.registrationlogin.repository;

import com.springboot.registrationlogin.Model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findByGenreAndMood(String genre, String mood);
}


