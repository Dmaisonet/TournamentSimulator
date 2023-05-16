package com.example.tournamentSimulator.controller;


import com.example.tournamentSimulator.entity.Participant;
import com.example.tournamentSimulator.error.ParticipantDoesNotExistException;
import com.example.tournamentSimulator.error.ParticipantNameAlreadyExistException;
import com.example.tournamentSimulator.service.ParticipantService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    private final Logger logger = LoggerFactory.getLogger(ParticipantController.class);

    @GetMapping({"/list", "/"})
    public ModelAndView getAllParticipants() {
        ModelAndView mv = new ModelAndView("list-participants");
        mv.addObject("participants", getParticipants());
        return mv;
    }

    @PostMapping("/addParticipant")
    public Participant saveParticipant(@Valid @RequestBody Participant participant) throws ParticipantNameAlreadyExistException {
        logger.info("Invoking save participant");
        return participantService.saveParticipant(participant);
    }

    @GetMapping("/getParticipant/{participantName}")
    public Participant getParticipantByName(@PathVariable("participantName") final String participantName) {
        logger.info("Invoking get participant by name");
        return participantService.getParticipantByName(participantName);
    }

    @GetMapping("/getParticipantList")
    public List<Participant> getParticipantList() {
        logger.info("Invoking get participant list");
        return  participantService.getParticipantList();
    }

    @DeleteMapping("/deleteParticipant")
    public String deleteParticipantByName(@RequestParam("participantName") final String participantName) throws ParticipantDoesNotExistException {
        logger.info("Invoking delete participant by name");
        return participantService.deleteParticipant(participantName);
    }

    private List<Participant> getParticipants() {
        return participantService.getParticipantList();
    }


}
