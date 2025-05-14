package com.example.order.common;

import com.example.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TransactionResponse {
    public TransactionResponse() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private Order order;
    private double amount;
    private String transactionId;
    private String message;

    public TransactionResponse(Order order, double amount, String transactionId, String message) {
        this.order = order;
        this.amount = amount;
        this.transactionId = transactionId;
        this.message = message;
    }
}
