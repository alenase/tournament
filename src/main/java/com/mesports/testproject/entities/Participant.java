package com.mesports.testproject.entities;

import com.mesports.testproject.exceptions.ErrorMessages;
import com.mesports.testproject.exceptions.TournamentException;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "participants")
@Data
public class Participant {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(nullable = false, name="participant_name")
    private String participantName;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "participants_in_matches",
            joinColumns = @JoinColumn(name = "match_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "participants_id", referencedColumnName = "id"))
    private List<Match> match;

    @ManyToMany(mappedBy = "participants", fetch = FetchType.LAZY)
    private List<Tournament> tournaments;

    public Participant() {
        tournaments = new ArrayList<>();
        match = new ArrayList<>();
    }

    public void setTournaments(Tournament tournament) {
        for (Tournament t : tournaments) {
            if (t.equals(tournament)) {
                throw new TournamentException(ErrorMessages.NOT_ALLOWED_QUANTITY);
            }
        }
        tournaments.add(tournament);
    }

    public void setMatch(Match match1) {
        for (Match m : match) {
            if (m.equals(match1)) {
                throw new TournamentException(ErrorMessages.PARTICIPANT_ALREADY_ADDED_TO_MATCH);
            }
        }
        match.add(match1);
    }

    @Override
    public String toString() {
        return "Participant(id=" + id + ", name=" + participantName + ", tournaments=[" + tournaments.size() + "]) ";
    }
}
