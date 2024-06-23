package com.sms.sms.repo;

import com.sms.sms.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findTop10ByOrderByDateDesc();
}