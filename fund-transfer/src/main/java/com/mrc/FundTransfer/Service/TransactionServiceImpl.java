package com.mrc.FundTransfer.Service;

import com.mrc.FundTransfer.Client.AccountFeignClient;
import com.mrc.FundTransfer.Constants.AppConstants;
import com.mrc.FundTransfer.Dto.AccountDto;
import com.mrc.FundTransfer.Dto.TransactionDateRangeDto;
import com.mrc.FundTransfer.Repository.TransactionRepository;
import com.mrc.FundTransfer.entity.Transactions;
import com.mrc.FundTransfer.exception.ApplicationException;
import com.mrc.FundTransfer.response.StatementResponse;
import com.mrc.FundTransfer.utils.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountFeignClient accountRepository;

    @Override
    public StatementResponse statement(long accountNo, String month, int year) {
        logger.info("Inside Bank-Service-Impl - statement()");
        List<Transactions> transactions = transactionRepository.findByMonthAndYear(accountNo, DateConverter.getMonthNumber(month),year);
        AccountDto account = accountRepository.findByAccountNumberAndStatusTrue(accountNo);
        if (account == null) {
            throw new ApplicationException(AppConstants.RECORD_NOT_EXITS, AppConstants.RECORD_NOT_FOUND_FOR_RANGE.replace("PARAM1",month).replace("PARAM2",""+year));
        }
        return new StatementResponse(account,transactions );
    }

    @Override
    public StatementResponse statementBetweenDate(TransactionDateRangeDto request) {
        List<Transactions> transactions = transactionRepository.findByAccountNumberAndTransactionDateBetween(request.getAccountNumber(),request.getFromDate(),request.getToDate());
        AccountDto account = accountRepository.findByAccountNumberAndStatusTrue(request.getAccountNumber());
        if (account == null) {
            throw new ApplicationException(AppConstants.RECORD_NOT_EXITS, AppConstants.RECORD_NOT_FOUND_FOR_RANGE.replace("PARAM1",""+request.getFromDate()).replace("PARAM2",""+request.getToDate()));
        }
        return new StatementResponse(account,transactions);
    }
}
