package com.example.order.common;

import com.example.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private Order order;
    private Payment payment;

    public Payment getPayment() {
        return payment;
    }

    public Order getOrder() {
        return order;
    }
}
