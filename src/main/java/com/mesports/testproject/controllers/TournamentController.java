package com.mesports.testproject.controllers;

import com.mesports.testproject.dto.TournamentDto;
import com.mesports.testproject.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin
@RequestMapping("/tournament")
public class TournamentController {

    private final TournamentService tournamentService;
    private final ModelMapper modelMapper;

    @Autowired
    public TournamentController(TournamentService tournamentService, ModelMapper modelMapper) {
        this.tournamentService = tournamentService;
        this.modelMapper = modelMapper;
    }

    /*@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTournament(@PathVariable long id) {

        TournamentDto tournamentDto = tournamentService.getTournament(id);

        return ResponseEntity.ok(tournamentDto);
    }
*/
}
