package com.ari.domaindrivendesign.application.command;

import java.util.List;

public record CreateOrderCommand(String cpf, List<CreateOrderItemCommand> orderItems) {
}
