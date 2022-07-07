package com.crif.asf.ShopService.model;

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
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart")
    private Integer idCart;

    @Column(name = "id_user")
    private String idUser;

    private Integer qty;

    private Boolean completed;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    public Cart(String idUser, Integer qty, Product product) {
	this.idUser = idUser;
	this.qty = qty;
	this.product = product;
	this.completed = false;
    }

    public Cart(Integer idCart) {
	this.idCart = idCart;
    }

}
