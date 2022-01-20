package com.mrc.FundTransfer.Controller;

import com.mrc.FundTransfer.Dto.FundTransferDto;
import com.mrc.FundTransfer.Service.FundTransferService;
import com.mrc.FundTransfer.response.FundTransferResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FundTransferController {
    Logger logger = LoggerFactory.getLogger(FundTransferController.class);

    @Autowired
    FundTransferService fundService;

    @PostMapping("/transfer")
    public FundTransferResponse transfer(@Valid @RequestBody FundTransferDto transfer){
        logger.info("Inside fund-controller - transfer()");
        return fundService.transfer(transfer);
    }
}
