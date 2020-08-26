package com.mesports.testproject.services;

import com.mesports.testproject.dto.TournamentDto;
import com.mesports.testproject.entities.Tournament;
import com.mesports.testproject.exceptions.ErrorMessages;
import com.mesports.testproject.exceptions.TournamentException;
import com.mesports.testproject.repository.TournamentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentServiceImpl implements TournamentService {

    private TournamentRepository tournamentRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TournamentServiceImpl(ModelMapper modelMapper, TournamentRepository tournamentRepository){
        this.modelMapper = modelMapper;
        this.tournamentRepository = tournamentRepository;
    }

    public Tournament getTournamentById(int id){
        Tournament tournament = getTournamentEntityById(id);
        return tournament; //modelMapper.map(tournament, TournamentDto.class);
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
