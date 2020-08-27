package com.mesports.testproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class TournamentDto {
    private int id;
    private String name;
    private int maxParticipants;
    private int matchQuantity;
    private List<MatchDto> matches;
    private List<ParticipantDto> participants;
}
