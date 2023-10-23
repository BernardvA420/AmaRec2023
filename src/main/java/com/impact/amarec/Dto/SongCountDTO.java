package com.impact.amarec.Dto;

public class SongCountDTO {
    private Long songId;
    private String songTitle;

    private String songArtist;

    private String songFilePath;

    private String songCover;

    private String userEmail;
    private Long count;

    public SongCountDTO(Long songId, String songTitle, String songArtist, String songFilePath, String songCover, Long count) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songFilePath = songFilePath;
        this.songCover = songCover;
        this.count = count;

    }

    // getters and setters

    public String getSongTitle() {
        return songTitle;
    }

    public Long getCount() {
        return count;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public String getSongFilePath() {
        return songFilePath;
    }

    public String getSongCover() {
        return songCover;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Long getSongId() {
        return songId;
    }

    @Override
    public String toString() {
        return "SongCountDTO{" +
                "songId=" + songId +
                ", songTitle='" + songTitle + '\'' +
                ", songArtist='" + songArtist + '\'' +
                ", songFilePath='" + songFilePath + '\'' +
                ", songCover='" + songCover + '\'' +
                '}';
    }
}

