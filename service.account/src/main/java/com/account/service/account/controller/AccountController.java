package com.account.service.account.controller;

import com.account.service.account.model.AccountCreationPayload;
import com.account.service.account.model.AccountEntity;
import com.account.service.account.model.BaseResponse;
import com.account.service.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("")
    public ResponseEntity<BaseResponse<Map<String, Object>>> createAccount(@RequestBody @Valid AccountCreationPayload account) {
        AccountEntity accountToCreate = accountService.createAccount(account);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("Customer account created", accountToCreate.getId());
        BaseResponse<Map<String, Object>> response = new BaseResponse<>(201, "Customer account created", responseData);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<BaseResponse<AccountEntity>> getAccountDetails(@PathVariable int customerNumber) {
        AccountEntity accountToFind = accountService.findAccount(customerNumber);
        if (accountToFind == null) {
            BaseResponse<AccountEntity> response = new BaseResponse<>(401, "Customer Account not found");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        BaseResponse<AccountEntity> response = new BaseResponse<>(302, "Customer Account found", accountToFind);
        return ResponseEntity.ok(response);
    }
}