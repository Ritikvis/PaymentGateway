package com.PaymentGateway.payment.Entity;

import com.PaymentGateway.payment.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double amount;
    @Enumerated(EnumType.STRING)
    private Status status;
    private double amountDeducted;
    private LocalTime time;



}
