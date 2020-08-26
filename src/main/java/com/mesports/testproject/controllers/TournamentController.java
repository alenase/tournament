package com.mesports.testproject.controllers;

import com.mesports.testproject.dto.ParticipantDto;
import com.mesports.testproject.dto.TournamentDto;
import com.mesports.testproject.entities.Participant;
import com.mesports.testproject.entities.Tournament;
import com.mesports.testproject.models.CreateTournamentModel;
import com.mesports.testproject.models.ParticipantsModelForTournament;
import com.mesports.testproject.models.TournamentModel;
import com.mesports.testproject.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin
@RequestMapping("/tournament")
public class TournamentController {

    private final TournamentServiceImpl tournamentService;
    private final ModelMapper modelMapper;

    @Autowired
    public TournamentController(TournamentServiceImpl tournamentService, ModelMapper modelMapper) {
        this.tournamentService = tournamentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "/{id}"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTournament(@PathVariable int id) {
        System.out.println(id);
        TournamentDto tournament = tournamentService.getTournamentById(id);
        return ResponseEntity.ok(modelMapper.map(tournament, TournamentModel.class));
    }

    @PutMapping//(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTournament(@RequestBody CreateTournamentModel createTournamentModel) {
        TournamentDto tournamentDto = modelMapper.map(createTournamentModel, TournamentDto.class);
        tournamentService.createTournament(tournamentDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
