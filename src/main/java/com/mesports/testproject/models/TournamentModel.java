package com.mesports.testproject.models;

import lombok.Data;

import java.util.Collection;

@Data
public class TournamentModel {
    private int id;
    private String name;
    private int maxParticipants;
    private int matchQuantity;
    private Collection<MatchModelForTournament> matches;
    private Collection<ParticipantsModelForTournament> participants;
}
