package com.example.tournamentSimulator.service;

import com.example.tournamentSimulator.entity.Participant;

import java.util.ArrayDeque;
import java.util.ArrayList;

public interface TournamentService {

    void enactTournament(ArrayList<Participant> tournament);
}
