package com.example.tournamentSimulator.repository;


import com.example.tournamentSimulator.entity.Participant;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    public Optional<Participant> findByUsername(String participantName);

}
