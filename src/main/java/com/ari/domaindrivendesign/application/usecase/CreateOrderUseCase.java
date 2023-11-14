package com.ari.domaindrivendesign.application.usecase;

import com.ari.domaindrivendesign.application.command.CreateOrderCommand;
import com.ari.domaindrivendesign.application.command.CreateOrderItemCommand;
import com.ari.domaindrivendesign.domain.entity.Customer;
import com.ari.domaindrivendesign.domain.entity.Order;
import com.ari.domaindrivendesign.domain.entity.OrderItem;
import com.ari.domaindrivendesign.domain.entity.Product;
import com.ari.domaindrivendesign.domain.exception.BusinessException;
import com.ari.domaindrivendesign.domain.repository.CustomerRepository;
import com.ari.domaindrivendesign.domain.repository.OrderRepository;
import com.ari.domaindrivendesign.domain.service.OrderLimitService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public CreateOrderUseCase(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public Order execute(CreateOrderCommand orderCommand) {
        Customer customer = customerRepository.findById(orderCommand.cpf())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Customer"));

        Order order = new Order(LocalDateTime.now());
        List<OrderItem> orderItemList = orderCommand.orderItems().stream()
                .map(this::createOrderItems)
                .toList();
        order.addOrderItem(orderItemList);
        OrderLimitService.validate(customer, order);
        return orderRepository.save(order);
    }

    private OrderItem createOrderItems(CreateOrderItemCommand command) {
        return new OrderItem(command.productId(), command.quantity(), command.price());
    }
}
