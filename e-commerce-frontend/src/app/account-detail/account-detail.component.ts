import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Account } from '../models/account';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-account-detail',
  templateUrl: './account-detail.component.html',
  styleUrls: ['./account-detail.component.css']
})
export class AccountDetailComponent implements OnInit {

  

  constructor(private route: ActivatedRoute,
    private accountService: AccountService) { }

  ngOnInit(): void {
    this.getDetail();
  }

  account !: Account
  
  getDetail() {
    let idAccount = this.route.snapshot.paramMap.get('id_account') || '';
    
    this.accountService.getDetail(idAccount).subscribe(res => {
      console.log(res);
      this.account = res});
  }

}
