package com.mrc.FundTransfer.Controller;

import com.mrc.FundTransfer.Dto.TransactionDateRangeDto;
import com.mrc.FundTransfer.Service.TransactionService;
import com.mrc.FundTransfer.response.StatementResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;

@RestController
public class StatementController {
    Logger logger = LoggerFactory.getLogger(StatementController.class);

    @Autowired
    TransactionService statementService;

    @GetMapping("/statement/{accountNo},{month},{year}")
    public StatementResponse statement(@Valid @PathParam("accountNo") long accountNo , @PathParam("month") String month,
                                       @Min(value = 4,message = "year should be 4 digits only.") @PathParam("year") int year){
        logger.info("Inside account-controller - statement()");
        return statementService.statement(accountNo,month,year);
    }
    @PostMapping("/statement")
    public StatementResponse statementBetweenDate(@Valid @RequestBody TransactionDateRangeDto request){
        logger.info("Inside account-controller - statementBetweenDate()");
        return statementService.statementBetweenDate(request);
    }
}
