package com.mesports.testproject.models;

import com.mesports.testproject.entities.Participant;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class MatchModelForTournament {
    private int id;
    private Date startTime;
    private Date finishTime;
    private int participant1Score;
    private int participant2Score;
    private List<Participant> participants;
}
