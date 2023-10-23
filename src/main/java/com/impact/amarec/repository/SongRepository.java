package com.impact.amarec.repository;

import com.impact.amarec.entity.Playlist;
import com.impact.amarec.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByGenreAndMood(String genre, String mood);

    Song findByFilePath(String filePath);

    List<Song> findByMood(String moodName);

    List<Song> findByGenre(String genreName);

    List<Song> findByPlaylist(Playlist playlist);
}



