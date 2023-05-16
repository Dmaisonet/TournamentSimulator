package com.example.tournamentSimulator.service;


import com.example.tournamentSimulator.entity.Participant;
import com.example.tournamentSimulator.error.ParticipantDoesNotExistException;
import com.example.tournamentSimulator.error.ParticipantNameAlreadyExistException;
import com.example.tournamentSimulator.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository repository;
    @Override
    public Participant saveParticipant(Participant participant) throws ParticipantNameAlreadyExistException{
        Optional<Participant> existingParticipant = repository.findByUsername(participant.getUsername());
        if(existingParticipant.isPresent()) {
            throw new ParticipantNameAlreadyExistException("Participant with username "
                    + participant.getUsername() + " already exist");
        }
        return repository.save(participant);
    }

    @Override
    public Participant updateParticipant(Participant participant) {
        return repository.save(participant);
    }

    @Override
    public Participant getParticipantByName(String participantName) {
        return repository.findByUsername(participantName).get();
    }

    @Override
    public String deleteParticipant(String participantName) throws ParticipantDoesNotExistException {
        Optional<Participant> existingParticipant = repository.findByUsername(participantName);
        if(existingParticipant.isEmpty()) {
            throw new ParticipantDoesNotExistException("Participant with name " +
                    participantName + " does not exist");
        }
        repository.delete(existingParticipant.get());
        return "Participant with name " + participantName + " has been deleted";
    }

    @Override
    public List<Participant> getParticipantList() {
        return repository.findAll();
    }

    @Override
    public void bulkUpdateParticipants(ArrayList<Participant> saveList) {
        repository.saveAll(saveList);
    }

//    @Override
//    public DataTablesOutput<Participant> findAll(DataTablesInput input) {
//        return repository.findAll(input);
//    }


}
