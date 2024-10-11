package com.PaymentGateway.payment.Controller;

import com.PaymentGateway.payment.DTOS.RefundDto;
import com.PaymentGateway.payment.Service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Refund")
public class RefundController {
    @Autowired
    private RefundService refundService;
    @PostMapping("addRefund")
    public ResponseEntity<String> createRefund(@RequestBody RefundDto refundDto) {
        try {
            refundService.createRefund(refundDto);
            return new ResponseEntity<>("Refund created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
