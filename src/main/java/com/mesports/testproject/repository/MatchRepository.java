package com.mesports.testproject.repository;

import com.mesports.testproject.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Match getMatchById(int id);
}
