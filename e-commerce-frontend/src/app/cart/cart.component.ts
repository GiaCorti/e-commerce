import { CartService } from './../services/cart.service';
import { Component, createEnvironmentInjector, OnInit } from '@angular/core';
import { Cart } from '../models/cart';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartList: Cart[] = []
  totItems = 0;
  format = ".jpg"
  rows = 5
  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.getCart();
  }

  getCart(): void {
    this.cartService.getCart().subscribe(c => {this.cartList=c; this.totItems= c.length});
  }

  removeFromCart(id: string): void{

  }

}
