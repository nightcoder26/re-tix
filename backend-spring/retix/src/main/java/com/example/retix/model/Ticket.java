package com.example.retix.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;
    private LocalDateTime eventDateTime;
    private String seatDetails;
    private double price;

    @ManyToOne
    @JoinColumn(name = "seller_id") // the seller
    private User seller;

    @ManyToOne
    @JoinColumn(name = "buyer_id") // the buyer
    private User buyer;

    private String status; // e.g., AVAILABLE, REQUESTED, SOLD

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public LocalDateTime getEventDateTime() { return eventDateTime; }
    public void setEventDateTime(LocalDateTime eventDateTime) { this.eventDateTime = eventDateTime; }

    public String getSeatDetails() { return seatDetails; }
    public void setSeatDetails(String seatDetails) { this.seatDetails = seatDetails; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public User getSeller() { return seller; }
    public void setSeller(User seller) { this.seller = seller; }

    public User getBuyer() { return buyer; }
    public void setBuyer(User buyer) { this.buyer = buyer; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
