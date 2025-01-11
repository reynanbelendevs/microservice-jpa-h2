package com.account.service.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.account.service.account.model.AccountTypeEntity;

public interface AccountTypeJpaImpRepository extends JpaRepository<AccountTypeEntity, Integer> {

}
