package com.ari.domaindrivendesign.domain.service;

import com.ari.domaindrivendesign.domain.entity.Customer;
import com.ari.domaindrivendesign.domain.entity.Order;
import com.ari.domaindrivendesign.domain.exception.BusinessException;

public class OrderLimitService {

    private OrderLimitService() {}

    public static void validate(Customer customer, Order order) {
        if (customer.getOrderLimit().compareTo(order.getTotalValue()) < 0) {
            throw new BusinessException(String.format("Invalid Order Limit for this Customer. Limit is %.2f and got %.2f", customer.getOrderLimit().doubleValue(), order.getTotalValue().doubleValue()));
        }
    }
}
