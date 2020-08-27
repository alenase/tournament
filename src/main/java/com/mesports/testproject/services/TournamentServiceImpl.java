package com.mesports.testproject.services;

import com.mesports.testproject.dto.MatchDto;
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

    public MatchDto getMatchById(int id) {
        Match match = matchRepository.getMatchById(id);
        return modelMapper.map(match, MatchDto.class);
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

        if (tournament.getMatches().size() <= 0) {
            int k = 0;
            System.out.println("Critical digit:" + tournament.getParticipants().size());
            for (int i = 0; i < tournament.getParticipants().size() / 2; i++) {

                Match match = new Match();
                match.setTournament(tournament);
                tournament.setMatches(match);
                match = matchRepository.save(match);

                match.setParticipants(participants.get(k));

                participants.get(k).setMatch(match);
                System.out.println(k + " " + participants.get(k));
                ++k;

                match.setParticipants(participants.get(k));

                participants.get(k).setMatch(match);
                System.out.println(k + " " + participants.get(k));
                ++k;
            }
        }

        tournament = tournamentRepository.save(tournament);
        return modelMapper.map(tournament, TournamentDto.class);
    }

    public TournamentDto createMatch(int id, MatchDto matchDto, List<Integer> listId) {
        Tournament tournament = tournamentRepository.findTournamentById(id);
        Match match = modelMapper.map(matchDto, Match.class);
        Participant p1 = participantRepository.getParticipantById(listId.get(0));
        Participant p2 = participantRepository.getParticipantById(listId.get(1));
        try {
            tournament.addParticipants(p1);
            tournament.addParticipants(p2);
        } finally {
            tournament.setMatches(match);
            match.setParticipants(p1);
            match.setParticipants(p2);
            match.setTournament(tournament);
            matchRepository.save(match);
            tournamentRepository.save(tournament);
        }
        return modelMapper.map(tournament, TournamentDto.class);
    }


    private Tournament getTournamentEntityById(long id) {
        Tournament tournament = tournamentRepository.findById(id).orElse(null);
        if (tournament == null) {
            throw new TournamentException(ErrorMessages.NO_TOURNAMENT_FOUND);
        }
        return tournament;
    }


}
