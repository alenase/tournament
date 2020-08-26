package com.mesports.testproject.dto;

import com.mesports.testproject.entities.Match;
import lombok.Data;

import java.util.List;

@Data
public class TournamentDto {
    private int id;
    private int maxParticipants;
    private int matchQuantity;
    private List<Match> matches;
}
