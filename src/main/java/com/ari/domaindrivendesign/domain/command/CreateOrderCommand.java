package com.ari.domaindrivendesign.domain.command;

import java.util.List;

public record CreateOrderCommand(String customerName, List<CreateOrderItemCommand> orderItems) {
}
