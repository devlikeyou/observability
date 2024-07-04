package com.dev.observability.picking.controller;

import com.dev.observability.picking.dto.PickingRequest;
import com.dev.observability.picking.dto.PickingResponse;
import com.dev.observability.picking.exceptions.InternalServerErrorException;
import com.dev.observability.picking.model.Picking;
import com.dev.observability.picking.service.PickingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Log4j2
@RestController
@RequestMapping("/pickings")
public class PickingController implements IPickingController {

    @Autowired
    private PickingService pickingService;

    @Override
    @PostMapping
    public ResponseEntity<PickingResponse> createPicking(String requestTraceId, PickingRequest pickingRequest) {

        log.info("Received a request to create a Picking");

        if(hasError(requestTraceId)) {
            log.error("Picking has an Internal Error");
            throw new InternalServerErrorException("Picking has an Internal Error");
        }

        String pickingId = pickingService.createPicking(Picking.builder()
                .orderId(pickingRequest.getOrderId())
                .storeId(pickingRequest.getStoreId()).build());

        log.info("Created Picking: {} ", pickingId);
        return ResponseEntity.ok(
                PickingResponse.builder().pickingId(pickingId).build()
        );

    }

    private boolean hasError(String value){
        return Arrays.asList("5","6","7").stream()
                .anyMatch(c-> c.equals(value.substring(0,1)));
    }

}
