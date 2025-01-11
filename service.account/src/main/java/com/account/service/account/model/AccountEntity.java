package com.account.service.account.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "account_table")
@Data
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "customer_name", nullable = false, length = 50)
    private String customerName;

    @NotBlank
    @Size(max = 20)
    @Column(name = "customer_mobile", nullable = false, length = 20)
    private String customerMobile;

    @NotBlank
    @Size(max = 50)
    @Column(name = "customer_email", nullable = false, length = 50, unique = true)
    private String customerEmail;

    @NotBlank
    @Size(max = 100)
    @Column(name = "address1", nullable = false, length = 100)
    private String address1;

    @Size(max = 100)
    @Column(name = "address2", length = 100)
    private String address2;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id") // Foreign key in AccountTypeEntity
    private List<AccountTypeEntity> accountTypes;

    public AccountEntity() {
    }

    public AccountEntity(int id, @NotBlank @Size(max = 50) String customerName,
            @NotBlank @Size(max = 20) String customerMobile, @NotBlank @Size(max = 50) String customerEmail,
            @NotBlank @Size(max = 100) String address1, @Size(max = 100) String address2,
            List<AccountTypeEntity> accountTypes) {
        this.id = id;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.customerEmail = customerEmail;
        this.address1 = address1;
        this.address2 = address2;
        this.accountTypes = accountTypes;
    }

    
}
