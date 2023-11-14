package com.ari.domaindrivendesign.domain.service;

import com.ari.domaindrivendesign.application.command.CreateOrderCommand;
import com.ari.domaindrivendesign.application.command.CreateOrderItemCommand;
import com.ari.domaindrivendesign.application.usecase.CreateOrderUseCase;
import com.ari.domaindrivendesign.domain.entity.Customer;
import com.ari.domaindrivendesign.domain.entity.Order;
import com.ari.domaindrivendesign.domain.entity.OrderItem;
import com.ari.domaindrivendesign.domain.exception.BusinessException;
import com.ari.domaindrivendesign.domain.repository.CustomerRepository;
import com.ari.domaindrivendesign.domain.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrderLimitServiceTest {

    Order order;

    @BeforeEach
    void before() {
        order = new Order(LocalDateTime.now());
        OrderItem orderItem = new OrderItem(1L, 1, BigDecimal.valueOf(100));
        order.addOrderItem(Collections.singletonList(orderItem));
    }

    @Test
    void shouldCreateOrderWithValidCustomerAndWithinLimit() {
        Customer customer = new Customer("123", BigDecimal.valueOf(1000.00));

        assertDoesNotThrow(() -> OrderLimitService.validate(customer, order));
    }

    @Test
    void shouldThrowExceptionForInvalidCustomerLimit() {
        Customer customer = new Customer("456", BigDecimal.valueOf(50.00));

        BusinessException exception = assertThrows(BusinessException.class, () -> OrderLimitService.validate(customer, order));
        assertEquals("Invalid Order Limit for this Customer. Limit is 50,00 and got 100,00", exception.getMessage());
    }

}
