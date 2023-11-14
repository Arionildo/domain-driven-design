package com.ari.domaindrivendesign.application.command;

import java.math.BigDecimal;

public record CreateOrderItemCommand(Long productId, int quantity, BigDecimal price) {
}
