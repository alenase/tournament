package com.mesports.testproject.models;

import lombok.Data;

/*
I have created a separate Model for creation tournament because it should not contain Matches as we generate them
 */
@Data
public class CreateTournamentModel {
    private String name;
    private int maxParticipants;
}
