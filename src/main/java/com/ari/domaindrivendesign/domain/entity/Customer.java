package com.ari.domaindrivendesign.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String cpf;
    private BigDecimal orderLimit;

    protected Customer() {}

    public Customer(String cpf, BigDecimal orderLimit) {
        this.cpf = cpf;
        this.orderLimit = orderLimit;
    }

    public String getCpf() {
        return cpf;
    }

    public BigDecimal getOrderLimit() {
        return orderLimit;
    }
}
