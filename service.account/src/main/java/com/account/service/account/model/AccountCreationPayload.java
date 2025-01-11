package com.account.service.account.model;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AccountCreationPayload {

    @NotBlank
    @Size(max = 50)
    @NotNull
    private String customerName;

    @NotBlank
    @Size(min = 11, max = 20)
    private String customerMobile;

    @NotBlank
    @Size(max = 50)
    @Email
    @NotNull
    private String customerEmail;

    @NotBlank
    @Size(max = 100)
    @NotNull
    private String address1;

    @Size(max = 100)
    private String address2;

}
