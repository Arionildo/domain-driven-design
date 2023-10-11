package com.ari.domaindrivendesign.application.usecase;

import com.ari.domaindrivendesign.domain.command.CreateOrderCommand;
import com.ari.domaindrivendesign.domain.command.CreateOrderItemCommand;
import com.ari.domaindrivendesign.domain.entity.Order;
import com.ari.domaindrivendesign.domain.entity.OrderItem;
import com.ari.domaindrivendesign.domain.entity.Product;
import com.ari.domaindrivendesign.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;

    public CreateOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(CreateOrderCommand orderCommand) {
        Order order = new Order(LocalDateTime.now());
        List<OrderItem> orderItemList = orderCommand.orderItems().stream()
                .map(i -> createOrderItems(i, order))
                .toList();
        order.addOrderItem(orderItemList);
        return orderRepository.save(order);
    }

    private OrderItem createOrderItems(CreateOrderItemCommand command, Order order) {
        Product product = new Product(command.productId());
        return new OrderItem(product, command.quantity(), command.price(), order);
    }
}
