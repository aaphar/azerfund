package com.azerfund.azerfund.entities;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "companies")
public class Company {
    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private double monthlyNeed;

    @NotNull
    private double monthlyContribution;

    @NotNull
    private double balance;
}
