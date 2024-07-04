package com.dev.observability.customer.service;


import com.dev.observability.customer.model.Customer;

public interface ICustomerService {
    public void createCustomer(String requestTraceId, Customer customer);
}
