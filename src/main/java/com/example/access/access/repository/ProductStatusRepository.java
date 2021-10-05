package com.example.access.access.repository;

import com.example.access.access.entity.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStatusRepository extends JpaRepository<ProductStatus,Long> {
}
