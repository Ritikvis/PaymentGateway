package com.PaymentGateway.payment.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundDto {
    private Long userId;
    private Long transactionId;

}
