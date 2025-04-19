package com.example.retix.service;

import com.example.retix.model.Ticket;
import com.example.retix.model.User;
import com.example.retix.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public List<Ticket> searchTickets(String keyword) {
        return ticketRepository.findByEventNameContainingIgnoreCase(keyword);
    }

    public List<Ticket> getTicketsListedBySeller(User seller) {
        return ticketRepository.findBySeller(seller);
    }

    public List<Ticket> getTicketsRequestedByBuyer(User buyer) {
        return ticketRepository.findByBuyer(buyer);
    }

    public List<Ticket> getTicketRequestsReceivedBySeller(User seller) {
        return ticketRepository.findBySellerAndStatus(seller, "REQUESTED");
    }
}
