package com.ari.domaindrivendesign.application.usecase;

import com.ari.domaindrivendesign.application.command.CreateOrderCommand;
import com.ari.domaindrivendesign.application.command.CreateOrderItemCommand;
import com.ari.domaindrivendesign.domain.entity.Customer;
import com.ari.domaindrivendesign.domain.entity.Order;
import com.ari.domaindrivendesign.domain.exception.BusinessException;
import com.ari.domaindrivendesign.domain.repository.CustomerRepository;
import com.ari.domaindrivendesign.domain.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateOrderUseCaseTest {

    @Test
    void shouldCreateOrderWithValidCustomerAndWithinLimit() {
        Customer validCustomer = new Customer("123", BigDecimal.valueOf(1000.00));
        CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
        when(customerRepository.findById("123")).thenReturn(java.util.Optional.of(validCustomer));

        OrderRepository orderRepository = Mockito.mock(OrderRepository.class);

        CreateOrderUseCase createOrderUseCase = new CreateOrderUseCase(orderRepository, customerRepository);

        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order savedOrder = invocation.getArgument(0);
            savedOrder.setId(1L);
            return savedOrder;
        });

        CreateOrderCommand orderCommand = new CreateOrderCommand("123", Collections.singletonList(new CreateOrderItemCommand(1L, 1, BigDecimal.TEN)));

        Order createdOrder = createOrderUseCase.execute(orderCommand);

        assertNotNull(createdOrder);
    }

    @Test
    void shouldThrowExceptionForInvalidCustomerLimit() {
        Customer invalidCustomer = new Customer("456", BigDecimal.valueOf(50.00));
        CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
        when(customerRepository.findById("456")).thenReturn(java.util.Optional.of(invalidCustomer));

        OrderRepository orderRepository = Mockito.mock(OrderRepository.class);

        CreateOrderUseCase createOrderUseCase = new CreateOrderUseCase(orderRepository, customerRepository);

        CreateOrderCommand orderCommand = new CreateOrderCommand("456", Collections.singletonList(new CreateOrderItemCommand(1L, 1, BigDecimal.valueOf(100))));

        BusinessException exception = assertThrows(BusinessException.class, () -> createOrderUseCase.execute(orderCommand));
        assertEquals("Invalid Order Limit for this Customer. Limit is 50,00 and got 100,00", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForInvalidCustomer() {
        CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
        when(customerRepository.findById(any())).thenReturn(java.util.Optional.empty());

        OrderRepository orderRepository = Mockito.mock(OrderRepository.class);

        CreateOrderUseCase createOrderUseCase = new CreateOrderUseCase(orderRepository, customerRepository);

        CreateOrderCommand orderCommand = new CreateOrderCommand("789", Collections.singletonList(new CreateOrderItemCommand(1L, 1, BigDecimal.TEN)));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> createOrderUseCase.execute(orderCommand));
        assertEquals("Invalid Customer", exception.getMessage());
    }

}
