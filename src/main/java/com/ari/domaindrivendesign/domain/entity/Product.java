package com.ari.domaindrivendesign.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product extends BaseEntity<Long> {
    private String name;
    private BigDecimal price;

    public Product(Long productId) {
        id = productId;
    }

    protected Product() {}

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
