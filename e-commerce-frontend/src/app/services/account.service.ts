import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Account} from '../models/account';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { AccountListDTO } from '../models/accountListDTO';

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

  editAccount(account: any, idAccount: string): Observable<any> {
    return this.http.put(`${this.url}${idAccount}`,account,this.httpOptions).pipe(
      catchError(this.handleError<any>('editAccount', ))
    );
  }

  getDetail(idAccount: string): Observable<Account> {
    return this.http.get<Account>(`${this.url}${idAccount}`,this.httpOptions).pipe(
      catchError(this.handleError<any>('insertAccount', ))
    );
  }

  addBalance(balance: number, idAccount: string): Observable<any> {
    return this.http.patch(`${this.url}${idAccount}?balance=${balance}`,this.httpOptions).pipe(
      catchError(this.handleError<any>('addBalance', ))
    );
    
  }

  changeRole(newrole: string, idAccount: string): Observable<any> {
    return this.http.patch(`${this.url}?role=${newrole}&id_user=${idAccount}`,this.httpOptions).pipe(
      catchError(this.handleError<any>('addBalance', ))
    );
  }

  getAllAccounts(): Observable<AccountListDTO[]> {
    return this.http.get<AccountListDTO[]>(`${this.url}`).pipe(
      catchError(this.handleError<any>('insertAccount', ))
    );
  }

  delete(idAccount: string): Observable<any>{
    return this.http.delete(`${this.url}${idAccount}`).pipe(
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
