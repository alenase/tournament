package com.mesports.testproject.services;

import com.mesports.testproject.dto.MatchDto;
import com.mesports.testproject.dto.ParticipantDto;
import com.mesports.testproject.dto.TournamentDto;

import java.util.List;
import java.util.Set;

public interface TournamentService {
    TournamentDto getTournamentById(long id);

    ParticipantDto getParticipantById(int id);

    MatchDto getMatchById(int id);

    TournamentDto createTournament(TournamentDto tournamentDto);

    ParticipantDto createParticipant(ParticipantDto participantDto);

    TournamentDto addParticipants(long id, Set<ParticipantDto> participants);

    TournamentDto deleteParticipants(long id, ParticipantDto participant);

    TournamentDto startTournament(long id);

    TournamentDto createMatch(int id, MatchDto matchDto, List<Integer> listId);
}
