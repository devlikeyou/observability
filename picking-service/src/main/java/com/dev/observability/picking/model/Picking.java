package com.dev.observability.picking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Picking {

    @Id
    private String id;

    private String storeId;
    private String orderId;

    private String status;

    private OffsetDateTime initialDate;
    private OffsetDateTime finalDate;

}
