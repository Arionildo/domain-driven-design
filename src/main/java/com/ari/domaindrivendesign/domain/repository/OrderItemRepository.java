package com.ari.domaindrivendesign.domain.repository;

import com.ari.domaindrivendesign.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>  {
}
