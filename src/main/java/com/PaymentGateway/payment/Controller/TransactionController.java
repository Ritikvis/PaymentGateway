package com.PaymentGateway.payment.Controller;

import com.PaymentGateway.payment.DTOS.TransactionDto;
import com.PaymentGateway.payment.Entity.Transaction;
import com.PaymentGateway.payment.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("addTransaction")
    public ResponseEntity<Transaction> AddTransactions(@RequestBody TransactionDto transactionDto){
        try {
            Transaction transaction = transactionService.AddTransactions(transactionDto);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("total-success/{userId}")
    public ResponseEntity<Integer> getTotalSuccessfulTransactions(@PathVariable Long userId) {
        int totalAmount = transactionService.getTotalSuccessfulTransactions(userId);
        return new ResponseEntity<>(totalAmount, HttpStatus.OK);
    }
    @DeleteMapping("failed")
    public ResponseEntity<String> deleteFailedTransactions() {
        transactionService.deleteFailedTransactions();
        return new ResponseEntity<>("Failed transactions deleted", HttpStatus.NO_CONTENT);
    }

}
