package com.azerfund.azerfund.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "pool")
public class Pool {
    @Id
    private String id = "SINGLE_POOL_INSTANCE";

    private double total;

    private double available;

}
