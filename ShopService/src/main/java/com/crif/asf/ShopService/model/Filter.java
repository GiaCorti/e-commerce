package com.crif.asf.ShopService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Filter {
    private Double minPrice;
    private Double maxPrice;
    private String order;

}
