package com.account.service.account.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private int transactionStatusCode;
    private String transactionStatusDescription;
    private Map<String, Object> additionalData = new HashMap<>();

    @JsonUnwrapped
    private T data;

    public BaseResponse(int transactionStatusCode, String transactionStatusDescription) {
        this.transactionStatusCode = transactionStatusCode;
        this.transactionStatusDescription = transactionStatusDescription;
    }

    public BaseResponse(int transactionStatusCode, String transactionStatusDescription, T data) {
        this.transactionStatusCode = transactionStatusCode;
        this.transactionStatusDescription = transactionStatusDescription;
        this.data = data;
    }

    public BaseResponse(int transactionStatusCode, String transactionStatusDescription, Map<String, Object> additionalData) {
        this.transactionStatusCode = transactionStatusCode;
        this.transactionStatusDescription = transactionStatusDescription;
        this.additionalData = additionalData != null ? additionalData : new HashMap<>();
    }

    @JsonAnySetter
    public void AdditionalData(String key, Object value) {
        this.additionalData.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }
}
