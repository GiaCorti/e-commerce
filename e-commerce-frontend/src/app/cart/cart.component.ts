import { CartService } from './../services/cart.service';
import { Component, createEnvironmentInjector, OnInit } from '@angular/core';
import { Cart } from '../models/cart';
import { ConfirmationService, MessageService, ConfirmEventType } from 'primeng/api';

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
  total = 0
  constructor(
    private cartService: CartService,
    private confirmationService: ConfirmationService, 
    private messageService: MessageService) { }

  ngOnInit(): void {
    this.getCart();
  }

  getCart(): void {

    this.cartService.getCart().subscribe(c => {
      this.cartList=c; 
      this.totItems= c.length;
      this.total = c.reduce((accumulator, cart) => {
        return accumulator + cart.product.price*cart.qty;
      }, 0);
    });
  }

  removeFromCart(id: string): void{
    this.cartService.removeFromCart(id).subscribe(_ => this.ngOnInit());
  }

  confirm(): void {
    this.confirmationService.confirm({
        message: 'Are you sure that you want to proceed with the purchase?',
        header: 'Confirmation',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {
            this.messageService.add({severity:'info', summary:'Confirmed', detail:'Confirmed purchase'});
        },
        reject: (type: any) => {
            switch(type) {
                case ConfirmEventType.REJECT:
                    this.messageService.add({severity:'error', summary:'Rejected', detail:'You have rejected the purchase'});
                break;
                case ConfirmEventType.CANCEL:
                    this.messageService.add({severity:'warn', summary:'Cancelled', detail:'You have cancelled the purchase'});
                break;
            }
        }
    });
}
}
