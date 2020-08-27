package com.mesports.testproject.services;

import com.mesports.testproject.dto.ParticipantDto;
import com.mesports.testproject.dto.TournamentDto;
import com.mesports.testproject.entities.Match;
import com.mesports.testproject.entities.Participant;
import com.mesports.testproject.entities.Tournament;
import com.mesports.testproject.exceptions.ErrorMessages;
import com.mesports.testproject.exceptions.TournamentException;
import com.mesports.testproject.repository.MatchRepository;
import com.mesports.testproject.repository.ParticipantRepository;
import com.mesports.testproject.repository.TournamentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class TournamentServiceImpl implements TournamentService {

    private TournamentRepository tournamentRepository;
    private ParticipantRepository participantRepository;
    private MatchRepository matchRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TournamentServiceImpl(ModelMapper modelMapper, TournamentRepository tournamentRepository,
                                 ParticipantRepository participantRepository, MatchRepository matchRepository) {
        this.modelMapper = modelMapper;
        this.tournamentRepository = tournamentRepository;
        this.participantRepository = participantRepository;
        this.matchRepository = matchRepository;
    }

    public TournamentDto getTournamentById(long id) {
        Tournament tournament = getTournamentEntityById(id);
        return modelMapper.map(tournament, TournamentDto.class);
    }

    public ParticipantDto getParticipantById(int id) {
        Participant participant = participantRepository.getParticipantById(id);
        return modelMapper.map(participant, ParticipantDto.class);
    }

    public TournamentDto createTournament(TournamentDto tournamentDto) {
        Tournament tournamentEntity = modelMapper.map(tournamentDto, Tournament.class);
        Tournament result = tournamentRepository.save(tournamentEntity);
        return modelMapper.map(result, TournamentDto.class);
    }

    public ParticipantDto createParticipant(ParticipantDto participantDto) {
        Participant participant = modelMapper.map(participantDto, Participant.class);
        Participant result = participantRepository.save(participant);
        return modelMapper.map(result, ParticipantDto.class);
    }

    public TournamentDto addParticipants(long id, Set<ParticipantDto> participants) {
        Tournament tournament = tournamentRepository.findTournamentById(id);
        for (ParticipantDto p : participants) {
            boolean ifContains = false;
            for (Participant o : tournament.getParticipants()) {
                if (o.getId() == p.getId()) {
                    ifContains = true;
                    break;
                }
            }
            if (!ifContains && tournament.getParticipants().size() < tournament.getMaxParticipants()) {
                System.out.println(tournament.getParticipants().size() + " " + tournament.getMaxParticipants());
                Participant participantEntity = modelMapper.map(p, Participant.class);

                tournament.setParticipants(participantEntity);
                participantEntity.setTournaments(tournament);
            }
        }
        Tournament result = tournamentRepository.save(tournament);
        return modelMapper.map(result, TournamentDto.class);
    }


    public TournamentDto deleteParticipants(long id, ParticipantDto participant) {
        Tournament tournament = tournamentRepository.findTournamentById(id);
        Participant participantEntity = participantRepository.getParticipantById(participant.getId());
        tournament.getParticipants().remove(participantEntity);
        Tournament result = tournamentRepository.save(tournament);
        return modelMapper.map(result, TournamentDto.class);
    }

    public TournamentDto startTournament(long id) {
        Tournament tournament = tournamentRepository.findTournamentById(id);
        List<Participant> participants = new ArrayList<>();

        for (Participant p : tournament.getParticipants()) {
            participants.add(p);
        }

        if (tournament.getMatches().size() < 0) {
            int k = 0;
            for (int i = 0; i < tournament.getMaxParticipants() / 2; i++) {
                Match match = new Match();
                match.setParticipants(participants.get(k++));
                match.setParticipants(participants.get(k++));
                match.setTournament(tournament);
                tournament.setMatches(match);
                matchRepository.save(match);
            }
        }
        tournament = tournamentRepository.save(tournament);
        return modelMapper.map(tournament, TournamentDto.class);
    }


    public Tournament saveTournament(Tournament tournament) {
        Tournament savedTournament = tournamentRepository.save(tournament);
        return savedTournament;
    }


    private Tournament getTournamentEntityById(long id) {
        Tournament tournament = tournamentRepository.findById(id).orElse(null);
        if (tournament == null) {
            throw new TournamentException(ErrorMessages.NO_TOURNAMENT_FOUND);
        }
        return tournament;
    }


}
