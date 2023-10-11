package com.ari.domaindrivendesign.domain.repository;

import com.ari.domaindrivendesign.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
