package com.PaymentGateway.payment.Service;


import com.PaymentGateway.payment.Entity.Transaction;
import com.PaymentGateway.payment.Entity.User;

import com.PaymentGateway.payment.Enum.Status;
import com.PaymentGateway.payment.Rrepository.TransactionRepository;
import com.PaymentGateway.payment.Rrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;


    public Transaction addTransaction(Transaction transaction) {
        // Fetch the user based on the user ID from the transaction object
        Optional<User> optionalUser = userRepository.findById(transaction.getUser().getUserId());

        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("User not found with ID: " + transaction.getUser().getUserId());
        }

        User user = optionalUser.get();

        // Populate transaction fields
        transaction.setUser(user);
        transaction.setTime(LocalTime.now());
        boolean isSuccess = new Random().nextBoolean();

        if (isSuccess) {
            transaction.setStatus(Status.SUCCESS);
            transaction.setAmountDeducted(transaction.getAmount());
        } else {
            transaction.setStatus(Status.FAILED);
            transaction.setAmountDeducted(transaction.getAmount() * 0.5); // 50% deduction on failure
        }

        // Save and return the transaction
        return transactionRepository.save(transaction);
    }


    public int getTotalSuccessfulTransactions(Long userId) {
        List<Transaction> successfulTransactions = transactionRepository.findByUserUserIdAndStatus(userId, Status.SUCCESS);
        int successful = 0;

        for (Transaction transaction : successfulTransactions) {
            successful++;
        }

        return successful;
    }

    public void deleteFailedTransactions() {
        List<Transaction> failedTransactions = transactionRepository.findByStatus(Status.FAILED);
        transactionRepository.deleteAll(failedTransactions);
    }
}
