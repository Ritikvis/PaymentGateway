package com.PaymentGateway.payment.Service;

import com.PaymentGateway.payment.DTOS.TransactionDto;
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


    public Transaction AddTransactions(TransactionDto transactionDto) throws Exception{
        Optional<User> optionalUser = userRepository.findById(transactionDto.getUserId());
        if(!optionalUser.isPresent()){
            throw new Exception("User not found");
        }
        User user = optionalUser.get();
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTime(LocalTime.now());
        boolean isSuccess = new Random().nextBoolean();
        if (isSuccess) {
            transaction.setStatus(Status.SUCCESS);
            transaction.setAmountDeducted(transactionDto.getAmount());
        } else {
            transaction.setStatus(Status.FAILED);
            transaction.setAmountDeducted(transactionDto.getAmount() * 0.5); // 50% deduction in case of failure
        }



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
