import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Account} from '../models/account';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  
 
  constructor(private http:HttpClient) { }

  url = "http://localhost:14002/account/"

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  insertAccount(account: any): Observable<any>{
    //console.log(account);
    return this.http.post(`${this.url}register/`,account,this.httpOptions).pipe(
      catchError(this.handleError<any>('insertAccount', ))
    );
    
  }

  getDetail(idAccount: string): Observable<Account> {
    return this.http.get<Account>(`${this.url}${idAccount}`,this.httpOptions).pipe(
      catchError(this.handleError<any>('insertAccount', ))
    );
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
