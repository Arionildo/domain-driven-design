package com.ari.domaindrivendesign.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity<Long> {

    private LocalDateTime orderDate;
    private BigDecimal totalValue;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    protected Order() {
    }

    public void addOrderItem(List<OrderItem> orderItem) {
        orderItems.addAll(orderItem);
        orderItems.forEach(o -> o.setOrder(this));
        calculateTotalValue(orderItems);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        orderItem.setOrder(null);
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
