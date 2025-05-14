package com.example.payment.service;

import com.example.payment.entity.Payment;
import com.example.payment.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;
    private Logger log= LoggerFactory.getLogger(PaymentService.class);
    public Payment doPayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentPreprocessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        log.info("Payment service request: {}",new ObjectMapper().writeValueAsString(payment));
        System.out.println("*********id*********="+getAuthUserId());
        return repository.save(payment);
    }
    public String paymentPreprocessing(){
        return new Random().nextBoolean()?"success":"false";
    }
    private Long getAuthUserId(){
        return (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    public Payment findPaymentByOrderId(int orderId) throws JsonProcessingException {
        Payment payment=repository.findByOrderId(orderId);
        log.info("Payment service findPaymentByOrderId: {}",new ObjectMapper().writeValueAsString(payment));
        return payment;
    }
}
