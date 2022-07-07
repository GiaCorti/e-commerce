package com.crif.asf.ShopService.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crif.asf.ShopService.model.Product;

public interface CatalogRepository extends JpaRepository<Product, Integer> {

    @Override
    Page<Product> findAll(Pageable pageable);

    boolean existsByName(String name);

    Page<Product> findByPriceBetweenOrderByPriceDesc(Double minPrice, Double maxPrice, Pageable pageable);

    Page<Product> findByPriceBetweenOrderByPriceAsc(Double minPrice, Double maxPrice, Pageable pageable);

}
