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
    private long id;

    @Column(nullable = false)
    private String name;

    /*@OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> match;
     */

    @ManyToMany(mappedBy = "participants", fetch = FetchType.LAZY)
    private List<Tournament> tournaments;

    public Participant() {
        tournaments = new ArrayList<>();

    }

    public void setTournaments(Tournament tournament) {
        for (Tournament t : tournaments) {
            if (t.equals(tournament)) {
                throw new TournamentException(ErrorMessages.NOT_ALLOWED_QUANTITY);
            }
        }
        tournaments.add(tournament);
    }

    @Override
    public String toString() {
        return "Participant(id=" + id + ", name=" + name + ", tournaments=[" + tournaments.size() + "]) ";
    }
}
