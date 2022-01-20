package com.mrc.FundTransfer.Client;

import com.mrc.FundTransfer.Dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "http://MS-ACCOUNT/account/")
public interface AccountFeignClient {

    @GetMapping("{accountNo}")
    AccountDto findByAccountNumberAndStatusTrue(@PathVariable("accountNo") long accountNo);

    @PostMapping("update")
    AccountDto save(@RequestBody AccountDto account);
}
