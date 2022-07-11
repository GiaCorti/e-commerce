package com.crif.asf.ShopService.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_order")
    private String idOrder;

    @ManyToOne
    @JoinColumn(name = "id_cart")
    private Cart cart;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    private Double total;

    public Order(String idOrder, Cart cart, LocalDateTime orderDate, Double total) {
	this.idOrder = idOrder;
	this.cart = cart;
	this.orderDate = orderDate;
	this.total = total;
    }

}
