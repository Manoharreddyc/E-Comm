package com.mrc.FundTransfer.Repository;

import com.mrc.FundTransfer.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Long> {

    @Query(value = "FROM Transactions as t where t.accountNumber = ?1 and extract(month from t.transactionDate)=?2 and extract(year from t.transactionDate)=?3")
    List<Transactions> findByMonthAndYear(long accountNo, int month, int year);

    List<Transactions> findByAccountNumberAndTransactionDateBetween(long accountNo, LocalDateTime fromDate, LocalDateTime toDate);
}
