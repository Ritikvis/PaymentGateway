package com.PaymentGateway.payment.Rrepository;

import com.PaymentGateway.payment.Entity.Transaction;
import com.PaymentGateway.payment.Enum.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByUserUserIdAndStatus(Long userId, Status status);
    List<Transaction> findByStatus(Status status);
    List<Transaction> findByUserUserId(Long userId);
}
