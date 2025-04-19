package com.example.retix.repository;

import com.example.retix.model.Ticket;
import com.example.retix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEventNameContainingIgnoreCase(String eventName);

    List<Ticket> findBySeller(User seller);

    List<Ticket> findByBuyer(User buyer);

    List<Ticket> findBySellerAndStatus(User seller, String status);
}
