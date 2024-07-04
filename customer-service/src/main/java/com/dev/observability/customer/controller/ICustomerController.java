package com.dev.observability.customer.controller;

import com.dev.observability.customer.dto.request.CustomerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


public interface ICustomerController {

    public ResponseEntity<Void> createCustomer(
            @RequestHeader(name = "requestTraceId", required = true) final String requestTraceId,
            @RequestBody final CustomerRequest customerRequest);

}
