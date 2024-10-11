package com.PaymentGateway.payment.Rrepository;

import com.PaymentGateway.payment.Entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund,Long> {
}
