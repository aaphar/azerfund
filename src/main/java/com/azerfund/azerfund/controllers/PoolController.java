package com.azerfund.azerfund.controllers;

import com.azerfund.azerfund.entities.Pool;
import com.azerfund.azerfund.exceptions.MessageResponse;
import com.azerfund.azerfund.services.pool.PoolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RestController
@RequestMapping("/api/pool")
public class PoolController {

    @Autowired
    PoolService poolService;

    @PostMapping("/create")
    public ResponseEntity<Pool> create(@Valid @RequestBody Pool request) {
        Pool campaign = poolService.savePool(request);
        return ResponseEntity.ok(campaign);
    }

    @PutMapping("/update")
    public ResponseEntity<Pool> update(@Valid @RequestBody Pool request) {
        Pool campaign = poolService.updatePool(request);
        return ResponseEntity.ok(campaign);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        boolean result = poolService.deletePool(id);
        if (result) {
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK, "Pool entity is deleted successfully"));
        }
        throw new RuntimeException("Campaign not deleted");
    }

    @GetMapping("/get")
    public ResponseEntity<Pool> get() {
        Pool u = poolService.getPool();
        return ResponseEntity.ok(u);
    }
}
