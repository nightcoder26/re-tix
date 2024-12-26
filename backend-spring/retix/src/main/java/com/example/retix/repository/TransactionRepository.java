package com.example.retix.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.retix.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Custom query methods can be added here if needed
}

