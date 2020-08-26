package com.mesports.testproject.entities;

import lombok.Data;

import javax.persistence.*;

import java.util.Calendar;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "match")
@Data
public class Match {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

   /* @ManyToOne
    @JoinColumn(name = "id")
    private Participant participant1;

    @ManyToOne
    @JoinColumn(name = "id")
    private Participant participant2;

    */

    @Column(nullable = false)
    private Calendar startTime;

    @Column(nullable = false)
    private Calendar finishTime;

    @Column
    private int participant1Score;

    @Column
    private int participant2Score;

    @ManyToOne
    @JoinColumn(name = "matches")
    private Tournament tournament;


}
