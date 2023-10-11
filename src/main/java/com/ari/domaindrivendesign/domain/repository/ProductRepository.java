package com.ari.domaindrivendesign.domain.repository;

import com.ari.domaindrivendesign.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
