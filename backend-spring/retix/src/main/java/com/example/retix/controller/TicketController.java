package com.example.retix.controller;

import com.example.retix.model.Ticket;
import com.example.retix.model.TicketDTO;
import com.example.retix.model.User;
import com.example.retix.service.TicketService;
import com.example.retix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketDTO ticketDTO) {
        // Fetch seller user from UserService
        User seller = null;
        if (ticketDTO.getSellerId() != null) {
            seller = userService.getUserById(ticketDTO.getSellerId()).orElse(null);
            if (seller == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Create Ticket entity from DTO
        Ticket ticket = new Ticket();
        ticket.setEventName(ticketDTO.getEventName());
        ticket.setEventDateTime(ticketDTO.getEventDateTime());
        ticket.setSeatDetails(ticketDTO.getSeatDetails());
        ticket.setPrice(ticketDTO.getPrice());
        ticket.setSeller(seller);
        ticket.setStatus("AVAILABLE");

        Ticket createdTicket = ticketService.createTicket(ticket);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id)
                .map(ticket -> new ResponseEntity<>(ticket, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Ticket> searchTickets(@RequestParam String eventName) {
        return ticketService.searchTickets(eventName);
    }
}
