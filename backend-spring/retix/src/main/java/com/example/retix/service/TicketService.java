package com.example.retix.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import com.example.retix.repository.TicketRepository;
import com.example.retix.model.Ticket;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAvailableTickets() {
        return ticketRepository.findByStatus("Available");
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> getTicketById(int id) {
        return ticketRepository.findById(id);
    }
}

