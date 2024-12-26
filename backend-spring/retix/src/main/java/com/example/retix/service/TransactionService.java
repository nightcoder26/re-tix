package com.example.retix.service;

import com.example.retix.model.Transaction;
import com.example.retix.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    // Constructor injection
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        transaction.setTransactionStatus("PENDING"); // Default status
        return transactionRepository.save(transaction);
    }

    public List<Transaction> createTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            transaction.setTransactionStatus("PENDING"); // Default status for batch transactions
        }
        return transactionRepository.saveAll(transactions);
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction updateTransactionStatus(Long id, String status) {
        Optional<Transaction> transactionOpt = transactionRepository.findById(id);
        if (transactionOpt.isPresent()) {
            Transaction transaction = transactionOpt.get();
            transaction.setTransactionStatus(status);
            return transactionRepository.save(transaction);
        }
        throw new IllegalArgumentException("Transaction not found with ID: " + id);
    }

    public void deleteTransaction(Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Transaction not found with ID: " + id);
        }
    }
}
