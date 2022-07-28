import { Component, OnInit } from '@angular/core';
import { AccountListDTO } from '../models/accountListDTO';
import { AccountService } from '../services/account.service';
import { AuthService } from '../services/auth.service';
import { ConfirmationService} from 'primeng/api';

@Component({
  selector: 'app-accounts-list',
  templateUrl: './accounts-list.component.html',
  styleUrls: ['./accounts-list.component.css']
})
export class AccountsListComponent implements OnInit {

  constructor(private accountService: AccountService,
     private authService: AuthService,
     private confirmationService: ConfirmationService) { }

  accounts !: AccountListDTO []
  totAccounts: number = 0;
  rows: number = 5;

  idExec: string = ""

  ngOnInit(): void {
    this.getList();
    this.authService.getUser().subscribe(res => this.idExec=res)
  }
  getList() {
    this.accountService.getAllAccounts().subscribe(res => {this.accounts= res
    console.log(res)
  this.totAccounts=this.accounts.length});
  }

  delete(userid: string): void {
    if(userid == this.idExec){
      this.accountService.delete(userid).subscribe(() => this.logOut());}
    else{
    this.accountService.delete(userid).subscribe(() => this.ngOnInit())}
  }

  confirmDelete(userid: string): void {
    console.log("click delete")
    this.confirmationService.confirm({
        message: 'Are you sure you want to DELETE this account?',
        header: 'Confirmation',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {
          this.delete(userid);
            
        },
        reject: () => {
          
            }
        }
    );
}
    

  logOut(){
    this.authService.logout();
    
    window.location.replace("/home")
  }

}
