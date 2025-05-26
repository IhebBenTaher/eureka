package com.example.order.controller;

import com.example.order.common.Payment;
import com.example.order.common.TransactionRequest;
import com.example.order.common.TransactionResponse;
import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;
    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request,@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws JsonProcessingException {
        String token = authorizationHeader.replace("Bearer ", "");
        System.out.println(token);
        return service.saveOrder(request,token);
    }
    @GetMapping("/book")
    public String bookOrder() {
        return "book call v2";
    }
}
