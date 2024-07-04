package com.dev.observability.picking.repository;

import com.dev.observability.picking.model.Picking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PickingRepository extends MongoRepository<Picking,String> {
}
