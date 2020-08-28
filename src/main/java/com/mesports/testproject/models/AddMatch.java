package com.mesports.testproject.models;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class AddMatch {
    private Date startTime;
    private Date finishTime;
    private int participant1Score;
    private int participant2Score;
    private List<Integer> participantsId;
}
