package com.mrc.FundTransfer.response;

import com.mrc.FundTransfer.Dto.AccountDto;
import com.mrc.FundTransfer.entity.Transactions;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StatementResponse {

    private String accountHolder;
    private double accountNumber;
    private double balance;
    private double openingBalance;
    private String type;
    List<Transactions> transactions;

    public StatementResponse(AccountDto account, List<Transactions> transactions) {
        this.accountHolder = account.getAccountHolder();
        this.accountNumber = account.getAccountNumber();
        this.balance = account.getBalance();
        this.openingBalance = account.getOpeningBalance();
        this.type = account.getType();
        this.transactions=transactions.stream().map(o->new Transactions(o)).collect(Collectors.toList());
    }
}
