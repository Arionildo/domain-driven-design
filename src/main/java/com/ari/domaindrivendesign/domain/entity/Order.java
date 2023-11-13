package com.ari.domaindrivendesign.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity<Long> {

    private LocalDateTime orderDate;
    private BigDecimal totalValue;

    @OneToMany(orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "order_id", nullable = false)
    private final List<OrderItem> orderItems = new ArrayList<>();

    public Order(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    protected Order() {
    }

    public void addOrderItem(List<OrderItem> orderItem) {
        orderItems.addAll(orderItem);
        calculateTotalValue(orderItems);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        calculateTotalValue(orderItems);
    }

    private BigDecimal calculateTotalValue(List<OrderItem> orderItems) {
        totalValue = BigDecimal.ZERO;

        for (OrderItem item : orderItems) {
            totalValue = totalValue.add(item.getTotalValue());
        }

        return totalValue;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
