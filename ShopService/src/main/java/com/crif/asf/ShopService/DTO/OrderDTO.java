package com.crif.asf.ShopService.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    String idOrder;
    String productName;
    Integer qty;
    Double price;
    LocalDateTime orderDate;
    Double total;

}
