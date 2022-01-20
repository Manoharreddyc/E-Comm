package com.mrc.FundTransfer.Repository;

import com.mrc.FundTransfer.entity.FundTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<FundTransfer,Long> {
}
