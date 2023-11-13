package com.ari.domaindrivendesign.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateOrderRequest(@NotBlank(message = "Customer CPF may not be blank") String cpf,
                                 @NotEmpty(message = "The list of order items may not be empty") List<CreateOrderItemRequest> orderItems) {
    public CreateOrderRequest(String cpf, List<CreateOrderItemRequest> orderItems) {
        this.cpf = cpf;
        this.orderItems = orderItems;
    }

    @Override
    public String cpf() {
        return cpf;
    }

    @Override
    public List<CreateOrderItemRequest> orderItems() {
        return orderItems;
    }
}
