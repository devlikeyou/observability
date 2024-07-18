package com.dev.observability.picking.controller;

import com.dev.observability.picking.dto.PickingRequest;
import com.dev.observability.picking.dto.PickingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface IPickingController {

    ResponseEntity<PickingResponse> createPicking(
            @RequestBody PickingRequest pickingRequest );

}
