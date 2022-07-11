package com.crif.asf.ShopService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartOrder {

    private Integer idProduct;
    private Integer qty;
}
