package com.mesports.testproject;

import com.mesports.testproject.dto.TournamentDto;
import com.mesports.testproject.entities.Match;
import com.mesports.testproject.entities.Participant;
import com.mesports.testproject.entities.Tournament;
import com.mesports.testproject.repository.MatchRepository;
import com.mesports.testproject.repository.ParticipantRepository;
import com.mesports.testproject.repository.TournamentRepository;
import com.mesports.testproject.services.TournamentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.hibernate.Hibernate;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@SpringBootApplication
public class TestProjectApplication {

    public static TournamentServiceImpl tounamentService;
    private static TournamentRepository tournamentRepository;
    private static ParticipantRepository participantRepository;
    private static MatchRepository matchRepository;
    private static ModelMapper modelMapper;

    @Autowired
    public TestProjectApplication(ModelMapper modelMapper, TournamentRepository tournamentRepository,
                                 ParticipantRepository participantRepository, MatchRepository matchRepository,
                                  TournamentServiceImpl tounamentService) {
        this.modelMapper = modelMapper;
        this.tournamentRepository = tournamentRepository;
        this.participantRepository = participantRepository;
        this.matchRepository = matchRepository;
        this.tounamentService = tounamentService;
    }
    public static void main(String[] args) {
        SpringApplication.run(TestProjectApplication.class, args);

        Match m = matchRepository.getMatchById(9);

        Participant p = participantRepository.getParticipantById(9);
        //System.out.println(p.getTournaments());
        Hibernate.initialize(p.getTournaments());

        m.setParticipants(p);
        p.setMatch(m);



        System.out.println(p);
        System.out.println(m);

        matchRepository.save(m);
        participantRepository.save(p);

       /* Tournament tournament1 = new Tournament();
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
*/

    }

}
