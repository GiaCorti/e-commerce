import { CartOrder } from '../models/cartOrder';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap, catchError, Observable, of } from 'rxjs';
import { Cart } from '../models/cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {


  private url = 'http://localhost:14001/cart';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json' })
  };
  
  constructor(private http: HttpClient) {
   }

   
  addItemToCart(p: CartOrder): Observable<any> {
    return this.http.post<CartOrder>(this.url, p, this.httpOptions)
      .pipe(
        tap(_ => console.log('product has been added to your cart')),
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

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); 
      window.alert(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }


}
