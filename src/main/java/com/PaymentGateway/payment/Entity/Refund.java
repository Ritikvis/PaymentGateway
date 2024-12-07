package com.PaymentGateway.payment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long refundId;

    private double amount;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
}
