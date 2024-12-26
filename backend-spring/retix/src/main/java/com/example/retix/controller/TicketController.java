package com.example.retix.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import com.example.retix.service.TicketService;
import com.example.retix.service.TransactionService;
import com.example.retix.model.Ticket;
import com.example.retix.model.Transaction;


@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/available")
    public List<Ticket> getAvailableTickets() {
        return ticketService.getAvailableTickets();
    }

    @PostMapping("/lists")
    public ResponseEntity<List<Transaction>> createTransactions(@RequestBody List<Transaction> transactions) {
        List<Transaction> createdTransactions = transactionService.createTransactions(transactions);
        return ResponseEntity.ok(createdTransactions);
    }
    
}

