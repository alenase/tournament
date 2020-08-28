package com.mesports.testproject.services;

import com.mesports.testproject.dto.MatchDto;
import com.mesports.testproject.dto.ParticipantDto;
import com.mesports.testproject.dto.TournamentDto;
import com.mesports.testproject.entities.Match;
import com.mesports.testproject.entities.Participant;
import com.mesports.testproject.entities.Tournament;
import com.mesports.testproject.repository.MatchRepository;
import com.mesports.testproject.repository.ParticipantRepository;
import com.mesports.testproject.repository.TournamentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestTournamentServiceImpl {

    @Mock
    TournamentServiceImpl tournamentService;

    @Mock
    ModelMapper modelMapper;

    @Mock
    ParticipantRepository participantRepository;

    @Mock
    MatchRepository matchRepository;

    @Mock
    TournamentRepository tournamentRepository;

    int id;
    Tournament tournamentEntity;
    Participant participantEntity;
    Match matchEntity;

    TournamentDto tournamentDto;
    ParticipantDto participantDto;
    MatchDto matchDto;
    TournamentDto resultTournamentDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        tournamentEntity = new Tournament();
        participantEntity = new Participant();
        matchEntity = new Match();

        tournamentDto = new TournamentDto();
        participantDto = new ParticipantDto();
        matchDto = new MatchDto();
        resultTournamentDto = new TournamentDto();

        tournamentEntity.setName("Test tournament");
        tournamentEntity.setMaxParticipants(8);
        tournamentEntity.setParticipants(participantEntity);
        tournamentEntity.setId(id);

        id = 2;
    }

    private TournamentDto getTournamentDto() {
        tournamentDto.setName("Test tournament");
        tournamentDto.setMaxParticipants(8);
        tournamentDto.setParticipants(getParticipants());
        //tournamentDto.setId(id);
        return tournamentDto;
    }

    private List<ParticipantDto> getParticipants() {
        List<ParticipantDto> list = new ArrayList<>();
        list.add(participantDto);
        return list;
    }

    @Test
    void getTournamentById() {
        //given
        when(tournamentRepository.findTournamentById(tournamentDto.getId())).thenReturn(tournamentEntity);
        when(modelMapper.map(tournamentEntity, TournamentDto.class)).thenReturn(resultTournamentDto);

        //when
        TournamentDto tournamentResponseDto = tournamentService.getTournamentById(id);

        //then
        verify(tournamentService).getTournamentById(id);
        verify(modelMapper).map(tournamentEntity, TournamentDto.class);
        assertThat(tournamentResponseDto, is(resultTournamentDto));
    }

    @Test
    void createTournament() {
        //given
        when(modelMapper.map(tournamentDto, Tournament.class)).thenReturn(tournamentEntity);
        when(tournamentRepository.save(tournamentEntity)).thenReturn(tournamentEntity);

        //when
        TournamentDto tournamentResponse = tournamentService.createTournament(tournamentDto);

        //then
        verify(tournamentRepository).save(tournamentEntity);
        verify(modelMapper).map(tournamentEntity, TournamentDto.class);
        assertThat(tournamentResponse, is(tournamentDto));
    }
}
