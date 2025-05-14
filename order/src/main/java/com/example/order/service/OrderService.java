package com.example.order.service;

import com.example.order.common.Payment;
import com.example.order.common.TransactionRequest;
import com.example.order.common.TransactionResponse;
import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {
    @Autowired
    private OrderRepository repository;
    @Autowired
    @Lazy
    private RestTemplate template;
    @Value("${microservice.payment.endpoints.endpoint.url}")
    private String endpoint;
    private Logger log= LoggerFactory.getLogger(OrderService.class);
    public TransactionResponse saveOrder(TransactionRequest request,String token) throws JsonProcessingException {
        String msg="";
        Order order=request.getOrder();
        Payment payment=request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        log.info("Order service request: {}",new ObjectMapper().writeValueAsString(request));
        // Create headers and add Authorization token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        // Wrap the request with headers
        HttpEntity<Payment> requestEntity = new HttpEntity<>(payment, headers);
        Payment response=template.postForObject(endpoint,requestEntity, Payment.class);
        log.info("Payment service response from Order service rest call: {}",new ObjectMapper().writeValueAsString(response));
        msg=response.getPaymentStatus().equals("success")?"successful":"failure";
        repository.save(order);
        return new TransactionResponse(order,response.getAmount(),response.getTransactionId(),msg);
    }
}
