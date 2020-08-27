package com.mesports.testproject.controllers;

import com.mesports.testproject.dto.MatchDto;
import com.mesports.testproject.dto.TournamentDto;
import com.mesports.testproject.models.MatchResultModel;
import com.mesports.testproject.models.TournamentModel;
import com.mesports.testproject.services.TournamentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final TournamentServiceImpl tournamentService;
    private final ModelMapper modelMapper;

    @Autowired
    public MatchController(TournamentServiceImpl tournamentService, ModelMapper modelMapper) {
        this.tournamentService = tournamentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getTournament(@PathVariable int id) {
        MatchDto matchDto = tournamentService.getMatchById(id);
        MatchResultModel matchResultModel = modelMapper.map(matchDto, MatchResultModel.class);
        matchResultModel.setParticipants1(matchDto.getParticipants().get(0).getName());
        matchResultModel.setParticipants2(matchDto.getParticipants().get(1).getName());

        if (matchResultModel.getParticipant1Score() > matchResultModel.getParticipant1Score()) {
            matchResultModel.setResult("Winner is " + matchDto.getParticipants().get(0).getName());
        } else if (matchResultModel.getParticipant1Score() < matchResultModel.getParticipant1Score()) {
            matchResultModel.setResult("Winner is " + matchDto.getParticipants().get(1).getName());
        } else
            matchResultModel.setResult("This match has not been played yet");

        return ResponseEntity.ok(matchResultModel);
    }


}
