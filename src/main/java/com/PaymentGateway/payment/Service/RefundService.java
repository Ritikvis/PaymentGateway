package com.PaymentGateway.payment.Service;

import com.PaymentGateway.payment.DTOS.RefundDto;
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
    public void createRefund(RefundDto refundDto) throws Exception {
        Transaction transaction = transactionRepository.findById(refundDto.getTransactionId())
                .orElseThrow(() -> new Exception("Transaction not found"));

        if (!transaction.getStatus().equals(Status.FAILED) || transaction.getAmountDeducted() == 0) {
            throw new Exception("Cannot create refund for this transaction");
        }


        User user = userRepository.findById(refundDto.getUserId())
                .orElseThrow(() -> new Exception("User not found"));

        // Create and save the refund
        Refund refund = new Refund();
        refund.setAmount(transaction.getAmountDeducted());
        refund.setUser(user);
        refund.setTransaction(transaction);

        refundRepository.save(refund);
    }
}
