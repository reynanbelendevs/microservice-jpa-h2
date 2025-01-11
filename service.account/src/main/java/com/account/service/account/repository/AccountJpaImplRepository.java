package com.account.service.account.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import com.account.service.account.model.AccountEntity;


public interface AccountJpaImplRepository extends JpaRepository<AccountEntity, Integer> {

    List<AccountEntity> findAll();

    @Transactional
    @Modifying
    AccountEntity save(AccountEntity accountEntity);


}
