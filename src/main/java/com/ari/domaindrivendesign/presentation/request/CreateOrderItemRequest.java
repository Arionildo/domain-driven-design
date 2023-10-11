package com.ari.domaindrivendesign.presentation.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public final class CreateOrderItemRequest {
    @NotNull(message = "Product ID may not be null")
    private Long productId;

    @NotNull(message = "Quantity may not be null")
    @Min(value = 1)
    private Integer quantity;

    @NotNull(message = "Price may not be null")
    private BigDecimal price;

    public CreateOrderItemRequest(Long productId, Integer quantity, BigDecimal price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
