package com.mesports.testproject.services;

import com.mesports.testproject.config.WebConfig;
import com.mesports.testproject.dto.MatchDto;
import com.mesports.testproject.dto.ParticipantDto;
import com.mesports.testproject.dto.TournamentDto;
import com.mesports.testproject.entities.Match;
import com.mesports.testproject.entities.Participant;
import com.mesports.testproject.entities.Tournament;
import com.mesports.testproject.repository.MatchRepository;
import com.mesports.testproject.repository.ParticipantRepository;
import com.mesports.testproject.repository.TournamentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = WebConfig.class)
@RunWith(MockitoJUnitRunner.class)
class TournamentServiceImplTest {

    @InjectMocks
    private TournamentServiceImpl tournamentService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private TournamentRepository tournamentRepository;

    @Test
    void getParticipantById() {
    // GIVEN
        int expectedParticipationId = 2;
        Participant expectedParticipant = new Participant();
        expectedParticipant.setId(expectedParticipationId);
        ParticipantDto expectedParticipantDto = new ParticipantDto();
        expectedParticipantDto.setId(expectedParticipationId);

    // WHEN
       when(participantRepository.getParticipantById(expectedParticipationId)).thenReturn(expectedParticipant);
       when(modelMapper.map(expectedParticipant, ParticipantDto.class)).thenReturn(expectedParticipantDto);
       ParticipantDto actualParticipant = tournamentService.getParticipantById(expectedParticipationId);

    // THEN
        assertNotNull(actualParticipant);
        assertEquals(expectedParticipant.getId(), expectedParticipantDto.getId());
    }

    @Test
    void createTournament() {
        // GIVEN
        int expectedParticipationId = 2;
        Tournament expectedTournament = new Tournament();
        expectedTournament.setId(expectedParticipationId);
        TournamentDto expectedTournamentDto = new TournamentDto();
        expectedTournamentDto.setId(expectedParticipationId);

        // WHEN
        Tournament actualTournament = new Tournament();
        when(modelMapper.map(expectedTournamentDto, Tournament.class)).thenReturn(expectedTournament);
        when(modelMapper.map(expectedTournament, TournamentDto.class)).thenReturn(expectedTournamentDto);
        when(tournamentRepository.save(expectedTournament)).thenReturn(expectedTournament);
        TournamentDto actualTournamentDto = tournamentService.createTournament(expectedTournamentDto);

        System.out.println(expectedTournamentDto);

        // THEN
        System.out.println(actualTournamentDto);
        assertNotNull(actualTournamentDto);
        assertEquals(expectedTournamentDto.getId(), actualTournamentDto.getId());
    }

    @Test
    void getMatchById() {
        // GIVEN
        int expectedMatchId = 2;
        Match expectedMatch = new Match();
        expectedMatch.setId(expectedMatchId);
        MatchDto expectedMatchDto = new MatchDto();
        expectedMatchDto.setId(expectedMatchId);

        // WHEN
        when(matchRepository.getMatchById(expectedMatchId)).thenReturn(expectedMatch);
        when(modelMapper.map(expectedMatch, MatchDto.class)).thenReturn(expectedMatchDto);
        MatchDto actualMatch = tournamentService.getMatchById(expectedMatchId);

        // THEN
        assertNotNull(actualMatch);
        assertEquals(expectedMatch.getId(), expectedMatchDto.getId());
    }

}