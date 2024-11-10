package com.azerfund.azerfund.services.company;

import com.azerfund.azerfund.entities.Company;

import java.util.List;

public interface CompanyService {

    Company saveCompany(Company company);

    Company updateCompany(Company company);

    Company findCompanyById(String id);

    Company findCompanyByCompanyName(String companyName);

    boolean deleteCompany(String id);

    List<Company> findAllCompanies();

    long countCompanies();
}
