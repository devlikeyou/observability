package com.dev.observability.order.model;

import com.dev.observability.order.clients.payment.vo.PaymentRequest;
import com.dev.observability.order.dto.ItemRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    private String id;

    private OffsetDateTime createdDate;
    private OrderStatus status;

    private String storeId;
    private String customerId;

    private String paymentId;
    private String pickingId;
    private List<Item> items;

}
