package com.ari.domaindrivendesign.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public final class CreateOrderRequest {
    @NotBlank(message = "Customer Name may not be blank")
    private String customerName;

    @NotEmpty(message = "The list of order items may not be empty")
    private List<CreateOrderItemRequest> orderItems;

    public CreateOrderRequest(String customerName, List<CreateOrderItemRequest> orderItems) {
        this.customerName = customerName;
        this.orderItems = orderItems;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<CreateOrderItemRequest> getOrderItems() {
        return orderItems;
    }
}
