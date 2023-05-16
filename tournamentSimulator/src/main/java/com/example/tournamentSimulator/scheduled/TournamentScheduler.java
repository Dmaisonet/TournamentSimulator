package com.example.tournamentSimulator.scheduled;


import java.text.SimpleDateFormat;
import java.util.*;

import com.example.tournamentSimulator.entity.Participant;
import com.example.tournamentSimulator.error.ParticipantNameAlreadyExistException;
import com.example.tournamentSimulator.service.ParticipantService;
import com.example.tournamentSimulator.service.TournamentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduled task that handles tournament scheduling.
 */
@Component
public class TournamentScheduler {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private TournamentService tournamentService;
    private static final Logger log = LoggerFactory.getLogger(TournamentScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private ArrayDeque<Participant> participants = new ArrayDeque<>();
    private ArrayList<Participant> scheduledParticipants = new ArrayList<>();


    /**
     * Method to schedule all the tournaments.
     * Takes the list of all participants, shuffles them and adds them to a queue.
     * The queue is used to fill up an 8-person tournament which is handled by the tournament service.
     */
    @Scheduled(fixedRate = 10000) // 10 seconds
    public void scheduleTournaments() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        //first get all participants, then shuffle them and add them to the queue.
        List<Participant> shuffledParticipants = participantService.getParticipantList();
        Collections.shuffle(shuffledParticipants);
        participants.addAll(shuffledParticipants);
        log.info("The amount of participants in the queue is : " + participants.size());
        while(participants.size() > 0) {
            if(scheduledParticipants.size() == 0) {
                scheduledParticipants.add(participants.poll());
            } else if(!hasParticipant(scheduledParticipants, participants.peek())) {
                scheduledParticipants.add(participants.poll());
            } else {
                participants.poll();
            }
            if(scheduledParticipants.size() == 8) {
                for(Participant p: scheduledParticipants) {
                    log.info(p.getUsername());
                }
                //we have a group o 8 unique participants ready to do a tournament
                ArrayList<Participant> tournament = new ArrayList(scheduledParticipants);
                tournamentService.enactTournament(tournament);
                scheduledParticipants.clear();
            }
        }
    }


    private static boolean hasParticipant(ArrayList<Participant> participants, Participant participant) {
        boolean hasParticipant = false;
        for(Participant p : participants) {
            if(p.getUsername().equals(participant.getUsername())) {
                hasParticipant = true;
                break;
            }
        }
        return hasParticipant;
    }
}
