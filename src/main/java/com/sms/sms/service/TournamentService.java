package com.sms.sms.service;

import com.sms.sms.entity.Tournament;
import com.sms.sms.repo.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    public List<Tournament> getLatestTournaments() {
        return tournamentRepository.findTop10ByOrderByDateDesc();
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

    @Scheduled(fixedRate = 86400000) // every 24 hours
    public void expireTournaments() {
        List<Tournament> tournaments = tournamentRepository.findAll();
        for (Tournament tournament : tournaments) {
            if (isTournamentExpired(tournament)) {
                deleteTournament(tournament.getId());
            }
        }
    }

    private boolean isTournamentExpired(Tournament tournament) {
        Date publishDate = tournament.getDate();
        Date currentDate = new Date();
        long diffInMillies = currentDate.getTime() - publishDate.getTime();
        long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);
        return diffInDays > 30; // expire after 30 days
    }


}