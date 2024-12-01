package com.PaymentGateway.payment.Controller;


import com.PaymentGateway.payment.Entity.Refund;
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
    public ResponseEntity<String> createRefund(@RequestBody Refund refund) {

        refundService.createRefund(refund);
        return new ResponseEntity<>("Refund created successfully", HttpStatus.CREATED);

    }
}
