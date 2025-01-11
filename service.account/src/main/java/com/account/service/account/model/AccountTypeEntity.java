package com.account.service.account.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "account_type_table")
public class AccountTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "customer_number", nullable = false, length = 50)
    private String customerNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false, length = 50)
    private AccountType accountType;

    @Column(name = "available_balance")
    private int availableBalance;
}

