package com.ari.domaindrivendesign.domain.command;

import java.util.List;

public record CreateOrderCommand(String cpf, List<CreateOrderItemCommand> orderItems) {
}
