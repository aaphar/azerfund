package com.azerfund.azerfund.repositories;

import com.azerfund.azerfund.entities.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {

    Optional<Company> findCompanyByName(String name);

}
