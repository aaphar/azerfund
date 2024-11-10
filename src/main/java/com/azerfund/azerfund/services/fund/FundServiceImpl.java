package com.azerfund.azerfund.services.fund;

import com.azerfund.azerfund.entities.Company;
import com.azerfund.azerfund.entities.Contribution;
import com.azerfund.azerfund.entities.Pool;
import com.azerfund.azerfund.repositories.CompanyRepository;
import com.azerfund.azerfund.services.contribution.ContributionService;
import com.azerfund.azerfund.services.pool.PoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class FundServiceImpl implements FundService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ContributionService contributionService;

    @Autowired
    PoolService poolService;

    @Override
    public void calculateFund() {
        List<Company> companies = companyRepository.findAll();
        List<Company> companiesInNeed = new ArrayList<>();

        for (Company company : companies) {
            log.info(
                    "Company: name: {}, balance: {}, monthly need: {}, monthly contribution: {}",
                    company.getName(),
                    company.getBalance(),
                    company.getMonthlyNeed(),
                    company.getMonthlyContribution()
            );

            // Track companies that need funds
            if (company.getMonthlyNeed() > 0) {
                companiesInNeed.add(company);
                log.info("Company: name: {} is in need of funds", company.getName());
            }

            // Process contributions
            if (company.getMonthlyContribution() > 0) {
                Contribution contribution = Contribution.builder()
                        .company(company)
                        .amount(company.getMonthlyContribution())
                        .date(new Date())
                        .build();

                company.setBalance(company.getBalance() + company.getMonthlyContribution());
                company.setMonthlyContribution(0); // Reset monthly contribution

                contributionService.saveContribution(contribution);
                log.info("Company: name: {} is contributing funds", company.getName());
            }
        }

        companyRepository.saveAll(companies); // Save updated companies

        // Allocate funds to companies in need
        Pool pool = poolService.getPool();
        double availableFunds = pool.getAvailable();
        double total = pool.getTotal();
        log.info("Total available funds in pool: {}", availableFunds);

        for (Company company : companiesInNeed) {
            double need = company.getMonthlyNeed();

            if (availableFunds >= need) {
                // Fully satisfy the need
                company.setBalance(company.getBalance() - need);
                company.setMonthlyNeed(0); // Need met
                availableFunds -= need;
                total -= need;
                pool.setAvailable(availableFunds);
                pool.setTotal(total);
                log.info("Company: {}'s need of {} is fully met.", company.getName(), need);
            } else if (availableFunds > 0) {
                // Partially satisfy the need
                company.setBalance(company.getBalance() - availableFunds);
                company.setMonthlyNeed(need - availableFunds); // Remaining need
                log.info("Company: {}'s need partially met. Remaining need: {}", company.getName(), company.getMonthlyNeed());
                pool.setAvailable(0);
                break;
            } else {
                log.info("Insufficient funds to meet the need of {}", company.getName());
            }
        }

        poolService.updatePool(pool); // Save updated pool state
        companyRepository.saveAll(companies); // Save updated companies
    }
}
