package com.azerfund.azerfund.controllers;

import com.azerfund.azerfund.entities.Contribution;
import com.azerfund.azerfund.exceptions.MessageResponse;
import com.azerfund.azerfund.services.contribution.ContributionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RestController
@RequestMapping("/api/contributions")
public class ContributionController {

    @Autowired
    ContributionService contributionService;

    @PostMapping("/create")
    public ResponseEntity<Contribution> create(@Valid @RequestBody Contribution request) {
        Contribution campaign = contributionService.saveContribution(request);
        return ResponseEntity.ok(campaign);
    }

    @PutMapping("/update")
    public ResponseEntity<Contribution> update(@Valid @RequestBody Contribution request) {
        Contribution campaign = contributionService.updateContribution(request);
        return ResponseEntity.ok(campaign);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        boolean result = contributionService.deleteContribution(id);
        if (result) {
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK, "Pool entity is deleted successfully"));
        }
        throw new RuntimeException("Campaign not deleted");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Contribution> get(@PathVariable String id) {
        Contribution u = contributionService.getContribution(id);
        return ResponseEntity.ok(u);
    }

    @GetMapping("/getByCompanyId/{companyId}")
    public ResponseEntity<List<Contribution>> getByName(@PathVariable String companyId) {
        List<Contribution> u = contributionService.findContributionByCompany(companyId);
        return ResponseEntity.ok(u);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Contribution>> list() {
        return ResponseEntity.ok(contributionService.findAllContributions());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(contributionService.countContributions());
    }

}