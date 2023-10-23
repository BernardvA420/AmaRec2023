package com.impact.amarec.service;

import com.impact.amarec.Dto.SongCountDTO;
import com.impact.amarec.entity.UserInteraction;
import com.impact.amarec.repository.UserInteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInteractionService {

    @Autowired
    private UserInteractionRepository userInteractionRepository;

    @Autowired
    public UserInteractionService(UserInteractionRepository userInteractionRepository) {
        this.userInteractionRepository = userInteractionRepository;
    }

    public List<UserInteraction> getAllUserInteractions() {
        return userInteractionRepository.findAll();
    }

    public List<SongCountDTO> getSongCounts() {
        return userInteractionRepository.findSongCounts();
    }

    public List<SongCountDTO> getSongCountsForUser(String userEmail) {
        return userInteractionRepository.findSongCountsForUser(userEmail);
    }
}

