package com.dev.observability.picking.service;

import com.dev.observability.picking.model.Picking;
import com.dev.observability.picking.repository.PickingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class PickingService implements IPickingService {

    @Autowired
    private PickingRepository pickingRepository;

    @Override
    public String createPicking(Picking picking) {

        picking.setInitialDate(OffsetDateTime.now());
        picking.setStatus("PENDING");

        Picking save = pickingRepository.save(picking);
        return save.getId();
    }
}
