package com.ari.domaindrivendesign.presentation.controller;

import com.ari.domaindrivendesign.application.usecase.CreateOrderUseCase;
import com.ari.domaindrivendesign.domain.command.CreateOrderItemCommand;
import com.ari.domaindrivendesign.domain.entity.Order;
import com.ari.domaindrivendesign.domain.command.CreateOrderCommand;
import com.ari.domaindrivendesign.presentation.request.CreateOrderItemRequest;
import com.ari.domaindrivendesign.presentation.request.CreateOrderRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody CreateOrderRequest request) {
        List<CreateOrderItemCommand> orderItemCommandList = request.getOrderItems().stream()
                .map(this::mapOrderItems)
                .toList();
        CreateOrderCommand orderCommand = new CreateOrderCommand(request.getCustomerName(), orderItemCommandList);
        return createOrderUseCase.execute(orderCommand);
    }

    private CreateOrderItemCommand mapOrderItems(CreateOrderItemRequest request) {
        return new CreateOrderItemCommand(request.getProductId(),
                request.getQuantity(),
                request.getPrice());
    }
}
