package com.azerfund.azerfund.services.contribution;

import com.azerfund.azerfund.entities.Company;
import com.azerfund.azerfund.entities.Contribution;
import com.azerfund.azerfund.entities.Pool;
import com.azerfund.azerfund.repositories.CompanyRepository;
import com.azerfund.azerfund.repositories.ContributionRepository;
import com.azerfund.azerfund.services.pool.PoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContributionServiceImpl implements ContributionService {

    @Autowired
    ContributionRepository contributionRepository;

    @Autowired
    PoolService poolService;

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Contribution saveContribution(Contribution contribution) {
        double amount = contribution.getAmount();
        Pool pool = poolService.addAmountToPool(amount);
        return contributionRepository.save(contribution);
    }

    @Override
    public Contribution updateContribution(Contribution contribution) {
        Contribution c = getContribution(contribution.getId());
        double amount = c.getAmount() - contribution.getAmount();
        Pool pool = poolService.addAmountToPool(amount);
        return contributionRepository.save(contribution);
    }

    @Override
    public Contribution getContribution(String id) {
        return contributionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contribution not found"));
    }

    @Override
    public boolean deleteContribution(String id) {
        try {
            contributionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting contribution: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<Contribution> findAllContributions() {
        return contributionRepository.findAll();
    }

    @Override
    public long countContributions() {
        return contributionRepository.count();
    }

    @Override
    public List<Contribution> findContributionByCompany(String companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return contributionRepository.findContributionByCompany(company);
    }
}
