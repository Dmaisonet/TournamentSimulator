package com.example.tournamentSimulator.service;


import com.example.tournamentSimulator.entity.Participant;
import com.example.tournamentSimulator.error.ParticipantDoesNotExistException;
import com.example.tournamentSimulator.error.ParticipantNameAlreadyExistException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.ArrayList;
import java.util.List;

public interface ParticipantService {
    public Participant saveParticipant(Participant participant) throws ParticipantNameAlreadyExistException;

    public Participant updateParticipant(Participant participant);

    public Participant getParticipantByName(final String participantName);

    public String deleteParticipant(String participantName) throws ParticipantDoesNotExistException;

    public List<Participant> getParticipantList();

    public void bulkUpdateParticipants(ArrayList<Participant> saveList);

//    DataTablesOutput<Participant> findAll(DataTablesInput input);
}
