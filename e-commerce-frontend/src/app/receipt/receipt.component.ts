import { CartService } from './../services/cart.service';
import { Component, OnInit } from '@angular/core';
import { Order } from '../models/order';

@Component({
  selector: 'app-receipt',
  templateUrl: './receipt.component.html',
  styleUrls: ['./receipt.component.css']
})
export class ReceiptComponent implements OnInit {

  orders: Order[] = []
  total = 0;

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.orders = this.cartService.getOrders();
    this.total = this.orders.reduce((accumulator, order) => {
      return accumulator + order.total;
    }, 0);
  }

}
