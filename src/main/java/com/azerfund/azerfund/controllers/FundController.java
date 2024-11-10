package com.azerfund.azerfund.controllers;

import com.azerfund.azerfund.services.fund.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RestController
@RequestMapping("/api/fund")
public class FundController {

    @Autowired
    FundService fundService;

    @GetMapping("/allocateFund")
    public ResponseEntity<?> get() {
        fundService.calculateFund();
        return ResponseEntity.ok("Fund allocated successfully");
    }

}
