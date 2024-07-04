package com.dev.observability.customer.service;

import com.dev.observability.customer.model.Customer;
import com.newrelic.api.agent.Trace;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService{

    @Trace
    @Override
    public void createCustomer(String requestTraceId, Customer customer) {
        log.info("Customer created Successfully {} ", customer);
    }

}
