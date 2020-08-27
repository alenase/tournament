package com.mesports.testproject.dto;

import com.mesports.testproject.entities.Participant;
import com.mesports.testproject.entities.Tournament;
import lombok.Data;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Data
public class MatchDto {
    private int id;
    private List<ParticipantDto> participants;
    private Date startTime;
    private Date finishTime;
    private Tournament tournament;
    private int participant1Score;
    private int participant2Score;
}
