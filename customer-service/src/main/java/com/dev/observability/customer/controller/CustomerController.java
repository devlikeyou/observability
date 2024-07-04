package com.dev.observability.customer.controller;


import com.dev.observability.customer.dto.request.CustomerRequest;
import com.dev.observability.customer.model.Customer;
import com.dev.observability.customer.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController implements ICustomerController {

    private final ICustomerService customerService;

    @Override
    @PostMapping
    public ResponseEntity<Void> createCustomer(String requestTraceId, CustomerRequest customerRequest) {
        log.info("Receiving customer {}", customerRequest);

        customerService.createCustomer(requestTraceId,
                Customer.builder()
                .id(customerRequest.getId())
                .email(customerRequest.getEmail())
                .document(customerRequest.getDocument())
                .build());

        return ResponseEntity.created(URI.create("")).build();
    }

}
