package com.mesports.testproject.controllers;

import com.mesports.testproject.dto.ParticipantDto;
import com.mesports.testproject.dto.TournamentDto;
import com.mesports.testproject.models.AddParticipantsToTournamentModel;
import com.mesports.testproject.models.CreateTournamentModel;
import com.mesports.testproject.models.TournamentModel;
import com.mesports.testproject.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

    private final TournamentServiceImpl tournamentService;
    private final ModelMapper modelMapper;

    @Autowired
    public TournamentController(TournamentServiceImpl tournamentService, ModelMapper modelMapper) {
        this.tournamentService = tournamentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getTournament(@PathVariable long id) {
        System.out.println(id);
        TournamentDto tournament = tournamentService.getTournamentById(id);
        return ResponseEntity.ok(modelMapper.map(tournament, TournamentModel.class));
    }

    @PostMapping
    public ResponseEntity createTournament(@RequestBody CreateTournamentModel createTournamentModel) {
        TournamentDto tournamentDto = modelMapper.map(createTournamentModel, TournamentDto.class);
        tournamentService.createTournament(tournamentDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/add-participants")
    public ResponseEntity addParticipants(@PathVariable long id,
                                          @RequestBody AddParticipantsToTournamentModel[] participants) {
        Set<ParticipantDto> participantsDto = new HashSet<>();
        for(AddParticipantsToTournamentModel p : participants){
            participantsDto.add(tournamentService.getParticipantById(p.getId()));
        }
        TournamentDto tournamentDto = tournamentService.addParticipants(id, participantsDto);
        return ResponseEntity.ok(modelMapper.map(tournamentDto, TournamentModel.class));
    }

    @DeleteMapping(path = "/{id}/remove-participants")
    public ResponseEntity removeParticipants(@PathVariable long id,
                                          @RequestBody AddParticipantsToTournamentModel participants) {
        ParticipantDto participantsDto = tournamentService.getParticipantById(participants.getId());
        TournamentDto tournamentDto = tournamentService.deleteParticipants(id, participantsDto);
        return ResponseEntity.ok(modelMapper.map(tournamentDto, TournamentModel.class));
    }

    @PostMapping(path = "/{id}/start")
    public ResponseEntity startTournament(@PathVariable long id) {
        tournamentService.startTournament(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
