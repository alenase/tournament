package com.mesports.testproject.entities;

import lombok.Data;

import javax.persistence.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "matches", schema = "targetSchemaName")
@Data
public class Match {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @ManyToMany(mappedBy = "match", fetch = FetchType.LAZY)
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




    @Override
    public String toString() {
        Date dateStart = null;
        if (!startTime.equals(null))  {dateStart = startTime; }

        Date dateFinish = null;
        if (!finishTime.equals(null))  {dateFinish = finishTime; }
        return "Match(id=" + id + ", startTime=" + dateStart + ", finishTime=" +
                dateFinish + ", participant1Score=" + participant1Score
                + ", participant2Score=" + participant2Score +
                ", Tournament: " + tournament.getName() + ", id=" +tournament.getId();
    }


}
