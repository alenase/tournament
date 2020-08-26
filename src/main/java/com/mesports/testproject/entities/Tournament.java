package com.mesports.testproject.entities;

import com.mesports.testproject.exceptions.ErrorMessages;
import com.mesports.testproject.exceptions.TournamentException;
import lombok.Data;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tournament", schema = "targetSchemaName")
@Data
public class Tournament {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column
    private String name;

    @Column(name = "max_participants", nullable = false)
    private int maxParticipants;

    @Column(name = "match_quantity", nullable = false)
    private int matchQuantity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tournament" , cascade = CascadeType.ALL)//, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> matches;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "participants_in_tournaments",
            joinColumns = @JoinColumn(name = "tournament_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "participants_id", referencedColumnName = "id"))
    private List<Participant> participants;

    public Tournament() {
        matches = new ArrayList<>();
        participants = new ArrayList<>();
    }

    private void setMatchQuantity(){}

    public void setMaxParticipants(int maxParticipants) {
        if (maxParticipants % 8 != 0) {
            throw new TournamentException(ErrorMessages.NOT_ALLOWED_QUANTITY);
        }
        this.maxParticipants = maxParticipants;

        int i = maxParticipants / 2;
        while (i % 2 == 0) {
            this.matchQuantity += i;
            i /= 2;
        }
        //and the final match
        this.matchQuantity++;
    }

    public void setParticipants(Participant participant) {
        for (Participant p : participants) {
            if (p.equals(participant)) {
                throw new TournamentException(ErrorMessages.NOT_ALLOWED_QUANTITY);
            }
        }
        participants.add(participant);
    }

    public void setMatches(Match match) {
        for (Match m : matches) {
            if (m.equals(matches)) {
                throw new TournamentException(ErrorMessages.MATCH_IS_PRESENT);
            }
        }
        matches.add(match);
    }

    @Override
    public String toString() {
        return "Tournament(id=" + id + ", name=" + name + ", maxParticipants=" + maxParticipants
                + ", matchQuantity=" + matchQuantity + ", matches=[" + matches.size()
                + "], participants=[" + participants.size() + "])";

    }

}
