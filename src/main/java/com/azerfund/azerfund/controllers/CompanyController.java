package com.azerfund.azerfund.controllers;

import com.azerfund.azerfund.entities.Company;
import com.azerfund.azerfund.exceptions.MessageResponse;
import com.azerfund.azerfund.services.company.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<Company> create(@Valid @RequestBody Company request) {
        Company campaign = companyService.saveCompany(request);
        return ResponseEntity.ok(campaign);
    }

    @PutMapping("/update")
    public ResponseEntity<Company> update(@Valid @RequestBody Company request) {
        Company campaign = companyService.updateCompany(request);
        return ResponseEntity.ok(campaign);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        boolean result = companyService.deleteCompany(id);
        if (result) {
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK, "Pool entity is deleted successfully"));
        }
        throw new RuntimeException("Campaign not deleted");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Company> get(@PathVariable String id) {
        Company u = companyService.findCompanyById(id);
        return ResponseEntity.ok(u);
    }

    @GetMapping("/getByName/{companyName}")
    public ResponseEntity<Company> getByName(@PathVariable String companyName) {
        Company u = companyService.findCompanyByCompanyName(companyName);
        return ResponseEntity.ok(u);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Company>> list() {
        return ResponseEntity.ok(companyService.findAllCompanies());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(companyService.countCompanies());
    }

}
