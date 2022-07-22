import { Component, OnInit } from '@angular/core';
import { AccountListDTO } from '../models/accountListDTO';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-accounts-list',
  templateUrl: './accounts-list.component.html',
  styleUrls: ['./accounts-list.component.css']
})
export class AccountsListComponent implements OnInit {

  constructor(private accountService: AccountService) { }

  accounts !: AccountListDTO []
  totAccounts: number = 6;
  rows: number = 5;

  ngOnInit(): void {
    this.getList();
  }
  getList() {
    this.accountService.getAllAccounts().subscribe(res => {this.accounts= res
    console.log(res)
  this.totAccounts=this.accounts.length});
    
  }

}
