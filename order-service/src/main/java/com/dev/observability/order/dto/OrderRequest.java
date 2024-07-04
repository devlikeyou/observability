package com.dev.observability.order.dto;

import com.dev.observability.order.clients.payment.vo.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    private String customerId;
    private String customerEmail;

    private PaymentRequest payment;
    private String storeId;

    private List<ItemRequest> items;


}
