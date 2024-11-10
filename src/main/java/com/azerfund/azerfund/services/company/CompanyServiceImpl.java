package com.azerfund.azerfund.services.company;

import com.azerfund.azerfund.entities.Company;
import com.azerfund.azerfund.repositories.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company findCompanyById(String id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    @Override
    public Company findCompanyByCompanyName(String companyName) {
        return companyRepository.findCompanyByName(companyName)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    @Override
    public boolean deleteCompany(String id) {
        try {
            companyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting company: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public long countCompanies() {
        return companyRepository.count();
    }
}
