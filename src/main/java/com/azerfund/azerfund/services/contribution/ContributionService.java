package com.azerfund.azerfund.services.contribution;

import com.azerfund.azerfund.entities.Contribution;

import java.util.List;

public interface ContributionService {

    Contribution saveContribution(Contribution contribution);

    Contribution updateContribution(Contribution contribution);

    Contribution getContribution(String id);

    boolean deleteContribution(String id);

    List<Contribution> findAllContributions();

    long countContributions();

    List<Contribution> findContributionByCompany(String companyId);
}
