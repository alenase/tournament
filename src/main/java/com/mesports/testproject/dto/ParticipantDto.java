package com.mesports.testproject.dto;

import com.mesports.testproject.entities.Match;
import lombok.Data;

@Data
public class ParticipantDto {
    private int id;
    private String name;
    private Match match;
}
