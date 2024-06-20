package com.sms.sms.service;

import com.sms.sms.entity.Tournament;
import com.sms.sms.repo.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Tournament getTournament(Long id) {
        return tournamentRepository.findById(id).orElseThrow();
    }

    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }
}