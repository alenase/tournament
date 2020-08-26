package com.mesports.testproject.services;

import com.mesports.testproject.dto.ParticipantDto;
import com.mesports.testproject.dto.TournamentDto;
import com.mesports.testproject.entities.Participant;
import com.mesports.testproject.entities.Tournament;
import com.mesports.testproject.exceptions.ErrorMessages;
import com.mesports.testproject.exceptions.TournamentException;
import com.mesports.testproject.repository.ParticipantRepository;
import com.mesports.testproject.repository.TournamentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentServiceImpl implements TournamentService {

    private TournamentRepository tournamentRepository;
    private ParticipantRepository participantRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TournamentServiceImpl(ModelMapper modelMapper, TournamentRepository tournamentRepository,
                                 ParticipantRepository participantRepository){
        this.modelMapper = modelMapper;
        this.tournamentRepository = tournamentRepository;
        this.participantRepository = participantRepository;
    }

    public TournamentDto getTournamentById(int id){
        Tournament tournament = getTournamentEntityById(id);
        return modelMapper.map(tournament, TournamentDto.class);
    }

    public ParticipantDto getParticipantById(int id){
        Participant participant = participantRepository.getParticipantById(id);
        return modelMapper.map(participant, ParticipantDto.class);
    }

    public TournamentDto createTournament(TournamentDto tournamentDto){
        Tournament tournamentEntity = modelMapper.map(tournamentDto, Tournament.class);
        Tournament result = tournamentRepository.save(tournamentEntity);
        return modelMapper.map(result, TournamentDto.class);
    }

    public ParticipantDto createParticipant(ParticipantDto participantDto){
        Participant participant = modelMapper.map(participantDto, Participant.class);
        Participant result = participantRepository.save(participant);
        return modelMapper.map(result, ParticipantDto.class);
    }

    public Tournament saveTournament(Tournament tournament){
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
