package com.ari.domaindrivendesign.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "order_items")
public final class OrderItem extends BaseEntity<Long> {

    @Column(name = "product_id")
    private Long product;

    private int quantity;
    private BigDecimal price;

    public OrderItem(Long product, int quantity, BigDecimal price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public BigDecimal getTotalValue() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

}
