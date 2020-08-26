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

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TestProjectApplication {

	@Autowired
	public static TournamentServiceImpl tounamentService;

	public static void main(String[] args) {
		SpringApplication.run(TestProjectApplication.class, args);

		Tournament tournament1 = new Tournament();
		tournament1.setMaxParticipants(8);
		System.out.println(tournament1);

		Participant p1 = new Participant();
		p1.setName("Participant-1");
		p1.setTournaments(tournament1);
		System.out.println(p1);

		Participant p2 = new Participant();
		p2.setName("Participant-2");
		p2.setTournaments(tournament1);
		System.out.println(p2);

		Participant p3 = new Participant();
		p3.setName("Participant-3");
		p3.setTournaments(tournament1);
		System.out.println(p3);

		Participant p4 = new Participant();
		p4.setName("Participant-4");
		p4.setTournaments(tournament1);
		System.out.println(p4);

		tournament1.setParticipants(p1);
		tournament1.setParticipants(p2);
		tournament1.setParticipants(p3);
		tournament1.setParticipants(p4);
		System.out.println(tournament1);

		tounamentService.saveTournament(tournament1);

	}

}
