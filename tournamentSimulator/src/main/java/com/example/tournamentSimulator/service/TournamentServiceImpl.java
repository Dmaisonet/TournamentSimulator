package com.example.tournamentSimulator.service;

import com.example.tournamentSimulator.entity.Participant;
import com.example.tournamentSimulator.scheduled.TournamentScheduler;
import jakarta.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class handles all the tournament battles
 */
@Service
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private ParticipantService participantService;

    private static final Logger log = LoggerFactory.getLogger(TournamentScheduler.class);

    private Random randomGenerator = new Random();

    @Override
    public void enactTournament (ArrayList<Participant> tournament) {
        log.info("Stage 1: " + tournament.toString());
        ArrayList<Participant> saveList = new ArrayList<>();
        //stage 1 round 1
        battle(tournament.get(0), tournament.get(1), tournament, saveList);
        //stage 1 round 2
        battle(tournament.get(1), tournament.get(2), tournament, saveList);
        //stage 1 round 3
        battle(tournament.get(2), tournament.get(3), tournament, saveList);
        //stage 1 round 4
        battle(tournament.get(3), tournament.get(4), tournament, saveList);
        log.info("Stage 2: " + tournament.toString());
        //stage 2 round 1
        battle(tournament.get(0), tournament.get(1), tournament, saveList);
        //stage 2 round 2
        battle(tournament.get(1), tournament.get(2), tournament, saveList);
        //Stage 3 final round
        battle(tournament.get(0), tournament.get(1), saveList);
        //save all results
        participantService.bulkUpdateParticipants(saveList);
    }

    /**
     * Enacts the final battle of the tournament
     * @param participant participant in slot 1
     * @param participant1 participant in slot 2
     */
    private void battle(Participant participant, Participant participant1, ArrayList<Participant> saveList) {
        log.info("Final Stage: " + participant.getUsername() + " vs. " + participant1.getUsername());
        int decider = randomGenerator.nextInt(2);
        if(decider < 1) {
            participant.incrementWins();
            participant.incrementTournamentWins();
            participant1.incrementLosses();
            log.info(participant.getUsername() + " Wins the tournament!");
        }else {
            participant.incrementLosses();
            participant1.incrementWins();
            participant1.incrementTournamentWins();
            log.info(participant1.getUsername() + " Wins the tournament!");
        }
        saveList.add(participant);
        saveList.add(participant1);
//        participantService.updateParticipant(participant);
//        participantService.updateParticipant(participant1);
    }

    /**
     * Enacts a battle between 2 tournament, updates their stats, and removes the losser from the tournament
     * @param participant participant in slot 1
     * @param participant1 participant in slot 2
     * @param tournament the list of all active participants in the tournament
     */
    private void battle(Participant participant, Participant participant1, ArrayList<Participant> tournament,
                        ArrayList<Participant> saveList) {
        log.info(participant.getUsername() + " vs. " + participant1.getUsername());
        int decider = randomGenerator.nextInt(2);
        if(decider < 1) {
            participant.incrementWins();
            participant1.incrementLosses();
            log.info(participant.getUsername() + " Wins");
            tournament.remove(participant1);
            saveList.add(participant1);
        }else {
            participant.incrementLosses();
            participant1.incrementWins();
            log.info(participant1.getUsername() + " Wins");
            tournament.remove(participant);
            saveList.add(participant);
        }

    }
}
