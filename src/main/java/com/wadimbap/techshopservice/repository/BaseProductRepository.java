package com.wadimbap.techshopservice.repository;

import com.wadimbap.techshopservice.model.BaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseProductRepository extends JpaRepository<BaseProduct, Long> {
}
