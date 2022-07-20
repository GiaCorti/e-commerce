import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-account-register',
  templateUrl: './account-register.component.html',
  styleUrls: ['./account-register.component.css']
})
export class AccountRegisterComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
    private accountService: AccountService) { }

  ngOnInit(): void {
  }

  registerForm = this.formBuilder.group({
    email: ['',Validators.requiredTrue],
    firstname: ['',Validators.required],
    lastname: ['',Validators.required],
    password: ['',Validators.required],
    birthday: ['',Validators.required]
  });

  onSubmit():void {
    let account = this.registerForm.value;

    console.log(account)
    
    //this.accountService.insertAccount(account).subscribe(() => this.location.back());
    
  }

}
