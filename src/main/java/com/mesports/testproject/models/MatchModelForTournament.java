package com.mesports.testproject.models;

import lombok.Data;

import java.sql.Date;

@Data
public class MatchModelForTournament {
    private int id;
    private Date startTime;
    private Date finishTime;
    private int participant1Score;
    private int participant2Score;
}
