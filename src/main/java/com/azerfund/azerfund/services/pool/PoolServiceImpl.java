package com.azerfund.azerfund.services.pool;

import com.azerfund.azerfund.entities.Pool;
import com.azerfund.azerfund.repositories.PoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PoolServiceImpl implements PoolService {

    private static final String SINGLE_POOL_INSTANCE = "SINGLE_POOL_INSTANCE";

    @Autowired
    PoolRepository poolRepository;

    @Override
    public Pool savePool(Pool pool) {
        return getOrCreatePool(pool);
    }

    @Override
    public Pool updatePool(Pool pool) {
        Pool p = getOrCreatePool(pool);
        p.setTotal(pool.getTotal());
        p.setAvailable(pool.getAvailable());
        return poolRepository.save(p);
    }

    @Override
    public Pool getPool() {
        return getOrCreatePool();
    }

    @Override
    public boolean deletePool(String id) {
        try {
            poolRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting pool: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public Pool addAmountToPool(double amount) {
        Pool pool = getOrCreatePool();
        double total = pool.getTotal() + amount;
        double available = pool.getAvailable() + amount;
        pool.setTotal(total);
        pool.setAvailable(available);
        log.info("Adding amount to pool: {}", amount);
        return poolRepository.save(pool);
    }

    public Pool getOrCreatePool(Pool p) {
        return poolRepository.findById(SINGLE_POOL_INSTANCE)
                .orElseGet(() -> {
                    Pool pool = Pool.builder()
                            .id(SINGLE_POOL_INSTANCE)
                            .total(p.getTotal())
                            .available(p.getAvailable())
                            .build();
                    return poolRepository.save(pool);
                });
    }

    public Pool getOrCreatePool() {
        return poolRepository.findById(SINGLE_POOL_INSTANCE)
                .orElseGet(() -> {
                    Pool pool = Pool.builder()
                            .id(SINGLE_POOL_INSTANCE)
                            .total(0)
                            .available(0)
                            .build();
                    return poolRepository.save(pool);
                });
    }

}
