package com.mesports.testproject.dto;

import com.mesports.testproject.entities.Participant;
import com.mesports.testproject.entities.Tournament;
import lombok.Data;

import java.util.Calendar;

@Data
public class MatchDto {
    private int id;
    private Participant participant1;
    private Participant participant2;
    private Calendar startTime;
    private Calendar finishTime;
    private Tournament tournament;
}
