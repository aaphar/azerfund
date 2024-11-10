package com.azerfund.azerfund.repositories;

import com.azerfund.azerfund.entities.Company;
import com.azerfund.azerfund.entities.Contribution;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContributionRepository extends MongoRepository<Contribution, String> {

    List<Contribution> findContributionByCompany(Company company);
}
