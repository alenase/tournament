package com.mesports.testproject.dto;

import com.mesports.testproject.entities.Match;
import com.mesports.testproject.entities.Tournament;
import lombok.Data;

import java.util.List;

@Data
public class ParticipantDto {
    private int id;
    private String name;
    private List<MatchDto> match;
    private List<TournamentDto> tournaments;
}
