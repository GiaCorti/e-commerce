import { OrderDTO } from './../models/orderDTO';
import { CartService } from './../services/cart.service';
import { Component, OnInit } from '@angular/core';
import { Order } from '../models/order';

@Component({
  selector: 'app-receipt',
  templateUrl: './receipt.component.html',
  styleUrls: ['./receipt.component.css']
})
export class ReceiptComponent implements OnInit {

  ordersDTO: OrderDTO[] = []
  total = 0;

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.ordersDTO = this.cartService.getOrders();
    this.total = this.ordersDTO.reduce((accumulator, orderDTO) => {
      return accumulator + orderDTO.price*orderDTO.qty;
    }, 0);
  }

}
