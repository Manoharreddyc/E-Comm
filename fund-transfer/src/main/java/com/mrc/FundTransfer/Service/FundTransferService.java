package com.mrc.FundTransfer.Service;

import com.mrc.FundTransfer.Dto.FundTransferDto;
import com.mrc.FundTransfer.response.FundTransferResponse;

public interface FundTransferService {
    FundTransferResponse transfer(FundTransferDto transfer);
}
