package com.mrc.FundTransfer.Service;

import com.mrc.FundTransfer.Client.AccountFeignClient;
import com.mrc.FundTransfer.Constants.AppConstants;
import com.mrc.FundTransfer.Dto.AccountDto;
import com.mrc.FundTransfer.Dto.FundTransferDto;
import com.mrc.FundTransfer.Repository.TransferRepository;
import com.mrc.FundTransfer.entity.FundTransfer;
import com.mrc.FundTransfer.entity.Transactions;
import com.mrc.FundTransfer.exception.ApplicationException;
import com.mrc.FundTransfer.helper.Helper;
import com.mrc.FundTransfer.response.FundTransferResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FundTransferServiceImpl implements FundTransferService {

    private static final String BACKEND = "backendA";

    Logger logger = LoggerFactory.getLogger(FundTransferServiceImpl.class);

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    AccountFeignClient accountFeignClient;


    @CircuitBreaker(name = BACKEND, fallbackMethod = "fallback")
    @Retry(name = BACKEND, fallbackMethod = "fallback")
    @Transactional
    @Override
    public FundTransferResponse transfer(FundTransferDto transferDto) {
        logger.debug("Inside Bank-Service-Impl - transfer()");
        String referenceNumber = Helper.generateReferenceNumber();
        AccountDto fromAccount = getAccount(transferDto.getFromAccountNumber());
        if (fromAccount != null) {
            if (transferDto.getFromAccountNumber() == transferDto.getToAccountNumber()) {
                throw new ApplicationException(AppConstants.INVALID_INPUT, AppConstants.ACCOUNT_NUMBER_SAME);
            }
            validateBalance(fromAccount, transferDto.getAmount());
            toAccountCheckAndUpdate(transferDto, referenceNumber);
            prepareTransferData(transferDto, transferDto.getFromAccountNumber(), referenceNumber, transferDto.getAmount(), 0, "BY TRANSFER " + transferDto.getFromAccountNumber() + " TO " + transferDto.getToAccountNumber() + " FOR " + transferDto.getRemarks().toUpperCase());
            fromAccount.setBalance(fromAccount.getBalance() - transferDto.getAmount());
            updateBalance(fromAccount);
        } else {
            throw new ApplicationException(AppConstants.RECORD_NOT_EXITS, AppConstants.ACCOUNT_NOT_EXITS_MSG + transferDto.getFromAccountNumber());
        }
        return new FundTransferResponse(AppConstants.TRANSACTION_MSG, referenceNumber);
    }

    private void toAccountCheckAndUpdate(FundTransferDto transfer, String referenceNumber) {
        AccountDto toAccount = getAccount(transfer.getToAccountNumber());
        if (null == toAccount) {
            throw new ApplicationException(AppConstants.RECORD_NOT_EXITS, AppConstants.ACCOUNT_NOT_EXITS + transfer.getFromAccountNumber());
        }
        toAccount.setBalance(toAccount.getBalance() + transfer.getAmount());
        updateBalance(toAccount);
        prepareTransferData(transfer, transfer.getToAccountNumber(), referenceNumber, 0, transfer.getAmount(), "TO TRANSFER " + transfer.getToAccountNumber() + " FROM " + transfer.getFromAccountNumber() + " FOR " + transfer.getRemarks().toUpperCase());
    }

    private void prepareTransferData(FundTransferDto transferDto, long accountNo, String referenceNumber, double debit, double credit, String transDetails) {
        FundTransfer fundTransfer = new FundTransfer(transferDto);
        fundTransfer.setTransactions(new Transactions(accountNo, referenceNumber, debit, credit, transDetails));
        transferRepository.save(fundTransfer);
    }

    private void updateBalance(AccountDto account) {
        accountFeignClient.save(account);
    }

    private void validateBalance(AccountDto account, double amount) {
        if (account.getBalance() < amount) {
            throw new ApplicationException(AppConstants.INSUFFICIENT_FUND, AppConstants.INSUFFICIENT_FUND_MSG);
        }
    }

    private AccountDto getAccount(long accountNo) {
        return accountFeignClient.findByAccountNumberAndStatusTrue(accountNo);
    }

    public FundTransferResponse fallback(FundTransferDto transferDto,RuntimeException e) {
        throw new ApplicationException("Server Down..","account service down...");
    }
}