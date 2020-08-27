package com.mesports.testproject.models;

import com.mesports.testproject.dto.ParticipantDto;
import lombok.Data;

import java.sql.Date;

@Data
public class MatchResultModel {
    private int id;
    private Date startTime;
    private Date finishTime;
    private int participant1Score;
    private int participant2Score;
    private String participants1;
    private String participants2;
    private String result;

}
