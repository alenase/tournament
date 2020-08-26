package com.mesports.testproject.dto;

import com.mesports.testproject.entities.Participant;
import com.mesports.testproject.entities.Tournament;
import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
public class MatchDto {
    private int id;
    private List<ParticipantDto> participants;
    private Calendar startTime;
    private Calendar finishTime;
    private Tournament tournament;
    private int participant1Score;
    private int participant2Score;
}
