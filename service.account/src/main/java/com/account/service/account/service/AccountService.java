package com.account.service.account.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.service.account.repository.AccountJpaImplRepository;
import com.account.service.account.model.AccountEntity;
import com.account.service.account.model.AccountType;
import com.account.service.account.model.AccountTypeEntity;
import com.account.service.account.model.AccountCreationPayload;

@Service
public class AccountService {

    @Autowired
    private AccountJpaImplRepository accountRepository;
    

    public List<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }

    public AccountEntity createAccount(AccountCreationPayload account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setCustomerName(account.getCustomerName());
        accountEntity.setCustomerEmail(account.getCustomerEmail());
        accountEntity.setCustomerMobile(account.getCustomerMobile());
        accountEntity.setAddress1(account.getAddress1());
        accountEntity.setAddress2(account.getAddress2());

        AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
        accountTypeEntity.setAccountType(AccountType.Savings);
        accountTypeEntity.setCustomerNumber(UUID.randomUUID().toString());
        accountTypeEntity.setAvailableBalance(0);

        accountEntity.setAccountTypes(List.of(accountTypeEntity));

        return accountRepository.save(accountEntity);
    }

    public AccountEntity findAccount(int customerNumber) {

        return accountRepository.findById(customerNumber).orElse(null);

    }


}
