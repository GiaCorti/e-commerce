import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Account } from '../models/account';
import { AccountService } from '../services/account.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'app-account-modify',
  templateUrl: './account-modify.component.html',
  styleUrls: ['./account-modify.component.css']
})
export class AccountModifyComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private accountService: AccountService,
    private formBuilder: FormBuilder,
    private location: Location) { }

  ngOnInit(): void {
    this.getDetail();
  }

  account !: Account

  editForm = this.formBuilder.group({
    email: ['',Validators.required],
    firstName: ['',Validators.required],
    lastName: ['',Validators.required],
    password: [''],
    birthday: ['',Validators.required],
  });

  getDetail() {
    let idAccount = this.route.snapshot.paramMap.get('id_account') || '';
    
    this.accountService.getDetail(idAccount).subscribe(res => {
      this.account = res
      this.fillForm(res);
    });
  }

  fillForm(account: Account) {
    this.editForm.patchValue(
      {email: account.email,
        firstName: account.firstName,
        lastName: account.lastName,
        birthday: account.birthday
      });
   
  }

  onSubmit(){
    if(this.editForm.value.password == ''){
        this.editForm.value.password= this.account.password;
    }
    let newaccount = this.editForm.value;
    let idAccount = this.route.snapshot.paramMap.get('id_account') || '';
    console.log(newaccount)

   this.accountService.editAccount(newaccount,idAccount).subscribe(() => this.location.back());
  }

}
