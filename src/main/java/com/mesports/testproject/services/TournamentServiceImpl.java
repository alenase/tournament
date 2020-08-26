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

    public TournamentDto getTournamentById(int id){
        Tournament tournament = getTournamentEntityById(id);
        return modelMapper.map(tournament, TournamentDto.class);
    }

    public void saveTournament(Tournament tournament){
         /*Tournament savedTournament = tournamentRepository.save(tournament);
        return modelMapper.map(savedTournament, TournamentDto.class);

          */
        tournamentRepository.save(tournament);
    }

    private Tournament getTournamentEntityById(long id) {
        Tournament tournament = tournamentRepository.findById(id).orElse(null);
        if (tournament == null) {
            throw new TournamentException(ErrorMessages.NO_TOURNAMENT_FOUND);
        }
        return tournament;
    }


}