package com.impact.amarec.repository;

import com.impact.amarec.Dto.SongCountDTO;
import com.impact.amarec.entity.UserInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInteractionRepository extends JpaRepository<UserInteraction, Long> {

    @Query("SELECT new com.impact.amarec.Dto.SongCountDTO(u.songid, s.title, s.song_cover, u.songlink, s.artist, COUNT(u.songid)) " +
            "FROM UserInteraction u " +
            "INNER JOIN Song s ON u.songid = s.id " +
            "GROUP BY u.songid, s.title, s.song_cover, u.songlink, s.artist " +
            "ORDER BY COUNT(u.songid) DESC")
    List<SongCountDTO> findSongCounts();

    @Query("SELECT new com.impact.amarec.Dto.SongCountDTO(u.songid, s.title, s.song_cover, u.songlink, s.artist, COUNT(u.songid)) " +
            "FROM UserInteraction u " +
            "INNER JOIN Song s ON u.songid = s.id " +
            "WHERE u.user_email = :userEmail " + // Add this line to filter by user's email
            "GROUP BY u.songid, s.title, s.song_cover, u.songlink, s.artist " +
            "ORDER BY COUNT(u.songid) DESC")
    List<SongCountDTO> findSongCountsForUser(@Param("userEmail") String userEmail);


}

