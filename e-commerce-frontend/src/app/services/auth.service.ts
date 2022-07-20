import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Token } from '../models/Token';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(private http:HttpClient) { }

  url = "http://localhost:14000/auth/"

  login(userinfo : string, email: any): void{
    this.http.get<Token>(`${this.url}login`,{headers: new HttpHeaders({
      'Authorization': `Basic ${userinfo}`
    })}).pipe(
      catchError(this.handleError<Token>('login', ))
    ).subscribe(res => {
      sessionStorage.setItem('token', res.token)
      sessionStorage.setItem('email', email);
    console.log(sessionStorage.getItem( 'token' ));
   // this.router.navigate(['/accounts/'])
  })
  }




  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); 
      //this.router.navigate([`/error/${error.status}`])
      //alert(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

}
