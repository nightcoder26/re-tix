package com.example.retix.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.retix.model.Ticket; // Adjust the package path as necessary

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByStatus(String status); // For available tickets
}

