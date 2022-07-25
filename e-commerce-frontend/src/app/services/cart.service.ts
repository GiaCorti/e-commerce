import { OrderDTO} from './../models/orderDTO';
import { CartOrder } from '../models/cartOrder';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap, catchError, Observable, of, Subject } from 'rxjs';
import { Cart } from '../models/cart';
import { Order } from '../models/order';

@Injectable({
  providedIn: 'root'
})
export class CartService {


  private url = 'http://localhost:14001/cart';

  ordersDTO: OrderDTO[] = [];

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {
  }

  public  changedCart= new Subject<boolean>();


  addItemToCart(p: CartOrder): Observable<any> {
    return this.http.post<CartOrder>(this.url, p, this.httpOptions)
      .pipe(
        tap(_ => {console.log('product has been added to your cart');this.changedCart.next(true);}),
        catchError(this.handleError<CartOrder>('addProduct'))
      );
  }

  getCart(): Observable<Cart[]> {
    return this.http.get<Cart[]>(this.url)
      .pipe(
        tap(_ => console.log('cart has been loaded')),
        catchError(this.handleError<Cart[]>('getCart', []))
      );
  }

  removeFromCart(id: string): Observable<any> {
    return this.http.delete<any>(this.url + "?id_product=" + id)
      .pipe(
        tap(_ => {console.log('product removed from cart');this.changedCart.next(true);}),
        catchError(this.handleError<any>('removeFromCart'))
      );
  }

  emptyCart(): Observable<any> {
    return this.http.delete<any>(this.url + "/empty")
    .pipe(
      tap(_ => {console.log('products removed from cart');this.changedCart.next(true);}),
      catchError(this.handleError<any>('emptyCart'))
    );
  }

  buy(): Observable<OrderDTO[]> {
    return this.http.post<any>(this.url+"/buy", null)
      .pipe(
        tap(orders => 
          {
            console.log('buy executed');
            this.ordersDTO = orders;
            this.changedCart.next(true);
          }),
        catchError(this.handleError<any>('buy'))
      );
  }

  getOrders(): OrderDTO[]{
    return this.ordersDTO;
  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      window.alert(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
