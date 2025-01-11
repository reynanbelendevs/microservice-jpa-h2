package com.account.service.account;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.http.MediaType;
import com.account.service.account.service.AccountService;
import com.account.service.account.model.AccountCreationPayload;
import com.account.service.account.model.AccountEntity;
import com.account.service.account.model.AccountType;
import com.account.service.account.model.AccountTypeEntity;


@TestPropertySource(locations = "classpath:application_test.yml")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@ActiveProfiles("test")
class ApplicationTests {

    public static final String route_api = "/api/v1/account";

    @Autowired
    private WebTestClient webClient;

    @MockBean

    private AccountService accountService;


    @Nested
    class AccountControllerMockTests {

        AccountEntity givenAccount() {
            AccountEntity mockAccount = new AccountEntity();

            mockAccount.setCustomerName("Renan");
            mockAccount.setCustomerEmail("renan@gmail.com");
            mockAccount.setCustomerMobile("1234567890");
            mockAccount.setAddress1("1234 Main St");
            mockAccount.setAddress2("");

            AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
            accountTypeEntity.setAccountType(AccountType.Savings);
            accountTypeEntity.setCustomerNumber(UUID.randomUUID().toString());
            accountTypeEntity.setAvailableBalance(0);

            mockAccount.setAccountTypes(List.of(accountTypeEntity));
            when(accountService.findAccount(any(Integer.class))).thenReturn(mockAccount);
            return mockAccount;
        }

        @Test
        void testAccounts_200() {
            AccountEntity mockAccount = givenAccount();
            int customerNumber = 1;
            webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(route_api + "/{customerNumber}")
                            .build(customerNumber))
                    .exchange()
                    .expectStatus()
                    .isOk()
                    .expectBody(AccountEntity.class)
                    .isEqualTo(mockAccount);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "NAN", "sample-customer"})
        void testGetAccounts_invalidCustomer_500(String CustomerNumber) {

            webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(route_api + "/{customerNumber}")
                            .build(CustomerNumber))
                    .exchange()
                    .expectStatus()
                    .is4xxClientError();
        }

        @Test
        void testCreateAccount_200() {

            AccountEntity mockAccount = givenAccount();
            AccountCreationPayload account = new AccountCreationPayload();
            account.setCustomerName("Renan");
            account.setCustomerEmail("renan@gmail.com");
            account.setCustomerMobile("+649123456789012");
            account.setAddress1("1234 Main St");
            account.setAddress2("");

            when(accountService.createAccount(any(AccountCreationPayload.class))).thenReturn(mockAccount);

            webClient.post()
                    .uri(route_api)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(account)
                    .exchange()
                    .expectStatus()
                    .isOk();


        }

        @Test
        void testCreateAccount_noName_400() {

            AccountEntity mockAccount = givenAccount();
            AccountCreationPayload account = new AccountCreationPayload();
            account.setCustomerName("");
            account.setCustomerEmail("renan@gmail.com");
            account.setCustomerMobile("+649123456789012");
            account.setAddress1("1234 Main St");
            account.setAddress2("");

            when(accountService.createAccount(any(AccountCreationPayload.class))).thenReturn(mockAccount);

            webClient.post()
                    .uri(route_api)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(account)
                    .exchange()
                    .expectStatus()
                    .isBadRequest();

        }

        @Test
        void testCreateAccount_noBody_400() {
            webClient.post()
                    .uri(route_api)
                    .exchange()
                    .expectStatus()
                    .isBadRequest();
        }


    }


}