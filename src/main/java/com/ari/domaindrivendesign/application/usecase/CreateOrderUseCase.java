package com.ari.domaindrivendesign.application.usecase;

import com.ari.domaindrivendesign.domain.command.CreateOrderCommand;
import com.ari.domaindrivendesign.domain.command.CreateOrderItemCommand;
import com.ari.domaindrivendesign.domain.entity.Customer;
import com.ari.domaindrivendesign.domain.entity.Order;
import com.ari.domaindrivendesign.domain.entity.OrderItem;
import com.ari.domaindrivendesign.domain.entity.Product;
import com.ari.domaindrivendesign.domain.exception.BusinessException;
import com.ari.domaindrivendesign.domain.repository.CustomerRepository;
import com.ari.domaindrivendesign.domain.repository.OrderRepository;
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
        if (customer.invalidOrderLimit(order.getTotalValue())) {
            throw new BusinessException(String.format("Invalid Order Limit for this Customer. Limit is %.2f and got %.2f", customer.getOrderLimit().doubleValue(), order.getTotalValue().doubleValue()));
        }
        return orderRepository.save(order);
    }

    private OrderItem createOrderItems(CreateOrderItemCommand command) {
        Product product = new Product(command.productId());
        return new OrderItem(product, command.quantity(), command.price());
    }
}
