import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AccountService } from '../services/account.service';
import {Location} from '@angular/common';


@Component({
  selector: 'app-account-register',
  templateUrl: './account-register.component.html',
  styleUrls: ['./account-register.component.css']
})
export class AccountRegisterComponent implements OnInit {
  

  constructor(private formBuilder: FormBuilder,
    private accountService: AccountService,
    private location: Location) { }

  ngOnInit(): void {
  }

  registerForm = this.formBuilder.group({
    email: ['',Validators.requiredTrue],
    firstName: ['',Validators.required],
    lastName: ['',Validators.required],
    password: ['',Validators.required],
    birthday: ['',Validators.required]
  });

  

  onSubmit():void {
    let account = this.registerForm.value;
    let d =new Date(Date.parse(account.birthday!));
    if(d.getMonth()<9 && d.getDate()<10){
    let date = `${d.getFullYear()}-0${d.getMonth()+1}-0${d.getDate()}`; account.birthday = date;}
    else if(d.getMonth()<9){let date = `${d.getFullYear()}-0${d.getMonth()+1}-${d.getDate()}`;account.birthday = date;}
    else if(d.getDate()<10){let date = `${d.getFullYear()}-${d.getMonth()+1}-0${d.getDate()}`;account.birthday = date;}
    else{let date = `${d.getFullYear()}-${d.getMonth()+1}-${d.getDate()}`;account.birthday = date;}
    //console.log(account);
   this.accountService.insertAccount(account).subscribe(() => this.location.back());
    
  }

}
