package com.example.retix.controller;

import com.example.retix.model.*;
import com.example.retix.service.TicketService;
import com.example.retix.service.UserService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final UserService   userService;

    public TicketController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService   = userService;
    }

    /* ------------------------- POST /api/tickets ------------------------- */
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketDTO dto) {

        if (dto.getSellerId() == null)      return ResponseEntity.badRequest().build();

        User seller = userService.getUserById(dto.getSellerId()).orElse(null);
        if (seller == null)                 return ResponseEntity.badRequest().build();

        Ticket t = new Ticket();
        t.setEventName(dto.getEventName());
        t.setEventDateTime(dto.getEventDateTime());   // assuming this setter exists
        t.setSeatDetails(dto.getSeatDetails());
        t.setPrice(dto.getPrice());
        t.setSeller(seller);
        t.setStatus(TicketStatus.AVAILABLE);          // ✅ enum, not String

        return new ResponseEntity<>(ticketService.createTicket(t), HttpStatus.CREATED);
    }

    /* ------------------------- GET /api/tickets -------------------------- */
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    /* ------------------------- GET /api/tickets/{id} --------------------- */
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id)
                .map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /* ------------------------- DELETE /api/tickets/{id} ------------------ */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    /* ------------------------- GET /api/tickets/search ------------------- */
    @GetMapping("/search")
    public List<Ticket> searchTickets(@RequestParam String eventName) {
        return ticketService.searchTickets(eventName);
    }

   @PostMapping("/reserve")
    public ResponseEntity<Ticket> reserveTicket(@RequestBody BookingRequestDTO dto) {
        Ticket reserved = ticketService.reserve(dto.getTicketId(), dto.getBuyerId());
        return ResponseEntity.ok(reserved);
    }
}
