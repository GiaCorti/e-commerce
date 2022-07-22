import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { Comment, newComment } from '../models/comment';
import { Filter } from '../models/filter';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  
  

  private url = 'http://localhost:14001/products';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json' })
  };

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

  getNumOfProducts() {
    return this.http.get<number>(this.url+"/totProducts")
    .pipe(
      tap(_ => console.log('totProducts has been loaded')),
      catchError(this.handleError<number>('getNumOfProducts'))
    );
  }

  getNumOfProductsFiltered(min: number, max: number) {
    return this.http.get<number>(this.url+"/totProductsFiltered?min="+min+"&max="+max)
    .pipe(
      tap(_ => console.log('totProductsFiltered has been loaded')),
      catchError(this.handleError<number>('getNumOfProductsFiltered'))
    );
  }


  getCatalog(page: number, rows: number): Observable<Product[]> {
    return this.http.get<Product[]>(this.url+"?page="+page+"&numElements="+rows)
      .pipe(
        tap(_ => console.log('catalog has been loaded')),
        catchError(this.handleError<Product[]>('getCatalog', []))
      );
  }

  getProduct(id: string): Observable<Product> {
    return this.http.get<Product>(this.url+"/"+id)
      .pipe(
        tap(_ => console.log('product has been loaded')),
        catchError(this.handleError<Product>('getProduct'))
      );
  }

    getFilteredCatalog(f: Filter, page: number, rows: number): Observable<Product[]> {
      return this.http.post<Product[]>(this.url+"/search?page="+page+"&numElements="+rows, f, this.httpOptions)
      .pipe(
        tap(_ => console.log('filtered catalog has been loaded')),
        catchError(this.handleError<Product[]>('getFilteredCatalog', []))
      );
  }

  modifyProduct(id: string, p: Product): Observable<Product> {
    return this.http.put<Product>(this.url+"/"+id, p, this.httpOptions)
      .pipe(
        tap(_ => console.log('product has been modified')),
        catchError(this.handleError<Product>('modifyProduct'))
      );
  }

  addProduct(p: Product): Observable<any> {
    return this.http.post<Product>(this.url, p, this.httpOptions)
      .pipe(
        tap(_ => console.log('product has been added')),
        catchError(this.handleError<Product>('addProduct'))
      );
  }

  getComments(idProd: string): Observable<Comment[]> {
    return this.http.get<Comment[]>(this.url+"/"+idProd+"/comments")
    
  }

  addComment(idProd: string, comment : newComment): Observable<any> {
    return this.http.post<newComment>(this.url+"/"+idProd+"/comments",comment,this.httpOptions).pipe(
      tap(_ => console.log('comment has been added')),
      catchError(this.handleError<any>('addComment'))
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
