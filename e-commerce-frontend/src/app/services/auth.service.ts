import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable, of, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  hasValidAccessToken() {
   const token = sessionStorage.getItem('token');
   return token !== null;
  }
  
  getAuthToken(): any {
    
    return sessionStorage.getItem('token');
  }
  

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(private http:HttpClient) { }

  url = "http://localhost:14000/auth/"

  public  isLogged= new Subject<boolean>(); 

  isAdmin(): Observable<boolean>{
    if(this.hasValidAccessToken()){
      const token = sessionStorage.getItem('token');
      return this.http.get<boolean>(`${this.url}isAdmin?token=${token}`).pipe(
        catchError(this.handleError<any>('isAdmin', ))
      )
    }
    return of(false);
  }

  getUser(): Observable<string>{
    if(this.hasValidAccessToken()){
      const token = sessionStorage.getItem('token');
      return this.http.get(`${this.url}getUser?token=${token}`,{responseType: "text"}).pipe(
        catchError(this.handleError<any>('getUser', ))
      )
    }
    return of("");
  }

  login(userinfo : string): Observable<any>{
    this.http.get(`${this.url}login`,{headers: new HttpHeaders({
      'Authorization': `Basic ${userinfo}`
    }),responseType: "text"}).pipe(
      catchError(this.handleError<any>('login', ))
    ).subscribe(res => {
      sessionStorage.setItem('token', res);
      this.isLogged.next(true);
    //console.log(sessionStorage.getItem( 'token' ));
    return of("")
  })
  return of("")
  }

  logout(){
    this.isLogged.next(false);
    sessionStorage.removeItem('token');
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
