package com.mesports.testproject.repository;

import com.mesports.testproject.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Participant getParticipantById(int id);
}
