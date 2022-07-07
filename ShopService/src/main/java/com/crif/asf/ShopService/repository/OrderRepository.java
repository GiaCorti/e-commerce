package com.crif.asf.ShopService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crif.asf.ShopService.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
