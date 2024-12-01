package com.PaymentGateway.payment.Service;

import com.PaymentGateway.payment.Entity.Refund;
import com.PaymentGateway.payment.Entity.Transaction;
import com.PaymentGateway.payment.Entity.User;
import com.PaymentGateway.payment.Enum.Status;
import com.PaymentGateway.payment.Rrepository.RefundRepository;
import com.PaymentGateway.payment.Rrepository.TransactionRepository;
import com.PaymentGateway.payment.Rrepository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefundService {
    @Autowired
    private RefundRepository refundRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    public void createRefund(Refund refund) {
        // Validate and fetch the associated transaction
        Transaction transaction = transactionRepository.findById(refund.getTransaction().getTransactionId())
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));

        // Check if refund can be created
        if (!transaction.getStatus().equals(Status.FAILED) || transaction.getAmountDeducted() == 0) {
            throw new IllegalArgumentException("Cannot create refund for this transaction");
        }

        // Validate and fetch the associated user
        User user = userRepository.findById(refund.getUser().getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Populate refund fields
        refund.setAmount(transaction.getAmountDeducted());
        refund.setUser(user);
        refund.setTransaction(transaction);

        // Save the refund
        refundRepository.save(refund);
    }

}
