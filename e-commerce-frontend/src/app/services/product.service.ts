import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private url = 'http://localhost:14001/products?page=0&numElements=15';

  mockCatalog: Product[] = [
    {
      id: 1,
      name: "Mouse",
      description: "An incredible gaming mouse",
      price: 75.99,
    },
    {
      id: 2,
      name: "Keyboard",
      description: "An incredible gaming keyboard",
      price: 40.0,
    }
];
  constructor(private http: HttpClient) { }


  /*
  getCatalog() {
    return of(this.mockCatalog)
  }*/

  
  getCatalog(): Observable<Product[]> {
    return this.http.get<Product[]>(this.url)
      .pipe(
        tap(_ => console.log('catalog has been loaded')),
        catchError(this.handleError<Product[]>('getCatalog', []))
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
