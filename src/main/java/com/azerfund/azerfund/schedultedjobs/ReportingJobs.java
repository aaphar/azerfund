package com.azerfund.azerfund.schedultedjobs;

import com.azerfund.azerfund.services.fund.FundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Slf4j
public class ReportingJobs {

    @Autowired
    FundService fundService;

    // Run at midnight on the 1st of every month
    @Scheduled(cron = "0 0 0 1 * ?")
    @Async("asyncTaskExecutor")
    public void check() {
        log.info("check begin...");

        fundService.calculateFund();

        log.info("check end...");
    }

}