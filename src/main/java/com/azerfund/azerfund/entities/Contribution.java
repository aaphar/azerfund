package com.azerfund.azerfund.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Data
@Builder
@Document(collection = "contributions")
public class Contribution {

    @Id
    private String id;

    private Company company;

    private double amount;

    private Date date;

}
