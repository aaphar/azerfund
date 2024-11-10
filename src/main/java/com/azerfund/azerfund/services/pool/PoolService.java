package com.azerfund.azerfund.services.pool;

import com.azerfund.azerfund.entities.Pool;

public interface PoolService {

    Pool savePool(Pool pool);

    Pool updatePool(Pool pool);

    Pool getPool();

    boolean deletePool(String id);

    Pool addAmountToPool(double amount);
}
