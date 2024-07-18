package com.dev.observability.picking.controller;

import com.dev.observability.picking.dto.PickingRequest;
import com.dev.observability.picking.dto.PickingResponse;
import com.dev.observability.picking.model.Picking;
import com.dev.observability.picking.service.PickingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/pickings")
public class PickingController implements IPickingController {

    @Autowired
    private PickingService pickingService;

    @Override
    @PostMapping
    public ResponseEntity<PickingResponse> createPicking(PickingRequest pickingRequest) {

        String pickingId = pickingService.createPicking(Picking.builder()
                .orderId(pickingRequest.getOrderId())
                .storeId(pickingRequest.getStoreId()).build());


        return ResponseEntity.ok(
                PickingResponse.builder().pickingId(pickingId).build()
        );

    }

}
