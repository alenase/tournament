package com.mesports.testproject;

import com.mesports.testproject.dto.TournamentDto;
import com.mesports.testproject.entities.Match;
import com.mesports.testproject.entities.Participant;
import com.mesports.testproject.entities.Tournament;
import com.mesports.testproject.repository.TournamentRepository;
import com.mesports.testproject.services.TournamentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@SpringBootApplication
public class TestProjectApplication {

    public static TournamentServiceImpl tounamentService;

    @Autowired
    public TestProjectApplication(TournamentServiceImpl tounamentService) {
        this.tounamentService = tounamentService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestProjectApplication.class, args);

        Tournament tournament1 = new Tournament();
        tournament1.setMaxParticipants(8);
        tournament1.setName("Tourm4");

        Participant p1 = new Participant();
        p1.setParticipantName("Participant-"+1234);
        p1.setTournaments(tournament1);
        tournament1.setParticipants(p1);

        Match match = new Match();
        match.setStartTime(new Date(456));
        match.setFinishTime(new Date(4567));
        match.setTournament(tournament1);

        match.setParticipants(p1);
        p1.setMatch(match);

        tournament1.setMatches(match);



        System.out.println(tournament1);
        System.out.println("match: " +match);
        System.out.println(p1);

        Tournament tournament4 = tounamentService.saveTournament(tournament1);

        System.out.println("HERE" + tournament4);


    }

}
