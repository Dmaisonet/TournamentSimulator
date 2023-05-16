package com.example.tournamentSimulator.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;


/**
 * Participant entity for users
 */
@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long participantId;
    @NotBlank(message= "Please enter a name")
    @Column(unique = true)
    private String username;
    private int wins;
    private int loss;
    private int tournamentWins;


    public Participant() {
    }

    public Participant(Long participantId, String username, int wins, int loss, int tournamentWins) {
        this.participantId = participantId;
        this.username = username;
        this.wins = wins;
        this.loss = loss;
        this.tournamentWins = tournamentWins;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public int getTournamentWins() {
        return tournamentWins;
    }

    public void setTournamentWins(int tournamentWins) {
        this.tournamentWins = tournamentWins;
    }

    public void incrementWins() {
        this.wins++;
    }

    public void incrementLosses() {
        this.loss++;
    }

    public void incrementTournamentWins() {
        this.tournamentWins++;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "participantId=" + participantId +
                ", username='" + username + '\'' +
                ", wins=" + wins +
                ", loss=" + loss +
                ", tournamentWins=" + tournamentWins +
                '}';
    }

}
