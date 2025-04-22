package com.example.retix.service;

import com.example.retix.model.*;
import com.example.retix.repository.BookingRequestRepository;
import com.example.retix.repository.TicketRepository;
import com.example.retix.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingRequestService {

    private final BookingRequestRepository bookingRequestRepo;
    private final TicketRepository ticketRepo;
    private final UserRepository userRepo;

    public BookingRequestService(BookingRequestRepository bookingRequestRepo, TicketRepository ticketRepo, UserRepository userRepo) {
        this.bookingRequestRepo = bookingRequestRepo;
        this.ticketRepo = ticketRepo;
        this.userRepo = userRepo;
    }

    public BookingRequest createRequest(Long ticketId, Long buyerId) {
        Ticket ticket = ticketRepo.findById(ticketId).orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        User buyer = userRepo.findById(buyerId).orElseThrow(() -> new IllegalArgumentException("Buyer not found"));

        if (!"AVAILABLE".equalsIgnoreCase(ticket.getStatus())) {
            throw new IllegalStateException("Ticket is not available for booking");
        }

        BookingRequest request = new BookingRequest();
        request.setTicket(ticket);
        request.setBuyer(buyer);
        request.setStatus(BookingRequest.Status.PENDING);
        request.setTimestamp(LocalDateTime.now());

        // Optionally update ticket status to REQUESTED
        ticket.setStatus("REQUESTED");
        ticketRepo.save(ticket);

        return bookingRequestRepo.save(request);
    }

    public BookingRequest respondToRequest(Long requestId, BookingRequest.Status status) {
        BookingRequest request = bookingRequestRepo.findById(requestId).orElseThrow(() -> new IllegalArgumentException("Booking request not found"));
        Ticket ticket = request.getTicket();

        // Check if the user responding is the seller of the ticket
        User seller = ticket.getSeller();
        // Here, we would need the user context or sellerId to verify authorization
        // For now, assume authorization is handled elsewhere or passed as parameter

        request.setStatus(status);

        if (status == BookingRequest.Status.ACCEPTED) {
            ticket.setStatus("SOLD");
            ticket.setBuyer(request.getBuyer());
            ticketRepo.save(ticket);
        } else if (status == BookingRequest.Status.DECLINED) {
            ticket.setStatus("AVAILABLE");
            ticketRepo.save(ticket);
        }

        return bookingRequestRepo.save(request);
    }

    public List<BookingRequest> getRequestsForSeller(Long sellerId) {
        return bookingRequestRepo.findByTicketSellerId(sellerId);
    }

    public List<BookingRequest> getRequestsForBuyer(Long buyerId) {
        return bookingRequestRepo.findByBuyerId(buyerId);
    }
}
