package com.crif.asf.ShopService.assembler;

import com.crif.asf.ShopService.DTO.OrderDTO;
import com.crif.asf.ShopService.model.Order;

public class OrderAssembler {

    public static OrderDTO assemble(Order o) {
	OrderDTO oDTO = new OrderDTO();

	oDTO.setIdOrder(o.getIdOrder());
	oDTO.setOrderDate(o.getOrderDate());
	oDTO.setPrice(o.getCart().getProduct().getPrice());
	oDTO.setProductName(o.getCart().getProduct().getName());
	oDTO.setQty(o.getCart().getQty());
	oDTO.setTotal(o.getTotal());

	return oDTO;

    }
}
