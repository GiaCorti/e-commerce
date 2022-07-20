import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Token } from '../models/Token';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  getAuthToken(): any {
    console.log(sessionStorage.getItem('token'))
    return sessionStorage.getItem('token');
  }
  

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(private http:HttpClient) { }

  url = "http://localhost:14000/auth/"

  login(userinfo : string): void{
    this.http.get(`${this.url}login`,{headers: new HttpHeaders({
      'Authorization': `Basic ${userinfo}`
    }),responseType: "text"}).pipe(
      catchError(this.handleError<any>('login', ))
    ).subscribe(res => {
      sessionStorage.setItem('token', res);
    console.log(sessionStorage.getItem( 'token' ));
   // this.router.navigate(['/accounts/'])
  })
  }




  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); 
      //this.router.navigate([`/error/${error.status}`])
      alert(`${operation} failed:${error.status}: ${error.message}`);
      return of(result as T);
    };
  }

}
