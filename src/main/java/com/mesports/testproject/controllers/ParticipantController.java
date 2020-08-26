package com.mesports.testproject.controllers;

import com.mesports.testproject.dto.ParticipantDto;
import com.mesports.testproject.dto.TournamentDto;
import com.mesports.testproject.entities.Participant;
import com.mesports.testproject.models.CreateTournamentModel;
import com.mesports.testproject.models.ParticipantsModelForTournament;
import com.mesports.testproject.models.TournamentModel;
import com.mesports.testproject.services.TournamentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    private final TournamentServiceImpl tournamentService;
    private final ModelMapper modelMapper;

    @Autowired
    public ParticipantController(TournamentServiceImpl tournamentService, ModelMapper modelMapper) {
        this.tournamentService = tournamentService;
        this.modelMapper = modelMapper;
    }

    @PutMapping
    public ResponseEntity createParticipant(@RequestBody ParticipantsModelForTournament participantsModelForTournament) {
        ParticipantDto participant = modelMapper.map(participantsModelForTournament, ParticipantDto.class);
        tournamentService.createParticipant(participant);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getParticipants(@PathVariable int id) {
        System.out.println(id);
        ParticipantDto participant = tournamentService.getParticipantById(id);
        return ResponseEntity.ok(participant);
    }
}
