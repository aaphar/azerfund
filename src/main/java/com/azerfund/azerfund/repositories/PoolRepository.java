package com.azerfund.azerfund.repositories;

import com.azerfund.azerfund.entities.Pool;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PoolRepository extends MongoRepository<Pool, String> {

}
