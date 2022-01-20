package com.mrc.FundTransfer.Service;

import com.mrc.FundTransfer.Dto.TransactionDateRangeDto;
import com.mrc.FundTransfer.response.StatementResponse;

public interface TransactionService {
    StatementResponse statement(long accountNo, String month, int year);

    StatementResponse statementBetweenDate(TransactionDateRangeDto request);
}
