package com.example.payment.controller;

import com.example.payment.entity.Payment;
import com.example.payment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.Id;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService service;
    @Autowired
    @Lazy
    private RestTemplate template;
    @PostMapping("/doPayment")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
        return service.doPayment(payment);
    }
    @GetMapping("/call")
    public String doPayment() {
        String response=template.getForObject("http://order/order/book",String.class);
        return "rest call from payment "+response;
    }
    @GetMapping("/{orderId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Payment findPaymentByOrderId(@PathVariable int orderId) throws JsonProcessingException {
        return service.findPaymentByOrderId(orderId);
    }
}