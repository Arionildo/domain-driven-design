package com.ari.domaindrivendesign.domain.command;

import java.math.BigDecimal;

public record CreateOrderItemCommand(Long productId, int quantity, BigDecimal price) {
}
