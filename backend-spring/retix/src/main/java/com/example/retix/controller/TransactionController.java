package com.example.retix.controller;


import com.example.retix.model.Transaction;
import com.example.retix.service.TransactionService; // Ensure this class exists in the specified package
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<Transaction>> createTransactions(@RequestBody List<Transaction> transactions) {
        List<Transaction> createdTransactions = transactionService.createTransactions(transactions);
        return ResponseEntity.ok(createdTransactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransactionStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            Transaction updatedTransaction = transactionService.updateTransactionStatus(id, status);
            return ResponseEntity.ok(updatedTransaction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}

