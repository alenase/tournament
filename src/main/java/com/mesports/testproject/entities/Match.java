package com.mesports.testproject.entities;

import com.mesports.testproject.exceptions.ErrorMessages;
import com.mesports.testproject.exceptions.TournamentException;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "matches", schema = "targetSchemaName")
@Data
public class Match {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @ManyToMany(mappedBy = "match", fetch = FetchType.EAGER)
    private List<Participant> participants;

    @Column(name = "start_time1")
    private Date startTime;

    @Column(name = "finish_time1")
    private Date finishTime;

    @Column(name = "participant1_score")
    private int participant1Score;

    @Column(name = "participant2_score")
    private int participant2Score;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    public Match() {
        participants = new ArrayList<>();
    }

    public void setParticipants(Participant participant) {
        for (Participant p : participants) {
            if (p.equals(participant)) {
                throw new TournamentException(ErrorMessages.NOT_ALLOWED_QUANTITY);
            }
        }
        if (participants.size() < 2) {
            participants.add(participant);
        } else
            throw new TournamentException(ErrorMessages.NOT_ALLOWED_QUANTITY_OF_PARTICIPANTS);
    }

    @Override
    public String toString() {
        Date dateStart = null;
        if (startTime != null) {
            dateStart = startTime;
        }
        Date dateFinish = null;
        if (finishTime != null) {
            dateFinish = finishTime;
        }
        return "Match(id=" + id + ", startTime=" + dateStart + ", finishTime=" + dateFinish +
                ", participantsList=" + participants
                + ", participant1Score=" + participant1Score
                + ", participant2Score=" + participant2Score +
                ", Tournament: " + tournament.getName() + ", id=" + tournament.getId();
    }

}
