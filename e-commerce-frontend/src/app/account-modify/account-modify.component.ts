import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Account } from '../models/account';
import { AccountService } from '../services/account.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-account-modify',
  templateUrl: './account-modify.component.html',
  styleUrls: ['./account-modify.component.css']
})
export class AccountModifyComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private accountService: AccountService,
    private formBuilder: FormBuilder,
    private location: Location,
    private authService: AuthService) { }
    isAdmin = false;
    roles = ["ADMIN","USER"]
  
    ngOnInit(): void {
    this.authService.isAdmin().subscribe(r => {
      this.isAdmin = r;
      this.getDetail();
      })
    
  }

  account !: Account

  editForm = this.formBuilder.group({
    email: ['',Validators.required],
    firstName: ['',Validators.required],
    lastName: ['',Validators.required],
    password: [''],
    birthday: ['',Validators.required],
  });

  balForm = this.formBuilder.group({
    balance:[0,Validators.required]
  });

  roleForm = this.formBuilder.group({
    role: ['',Validators.required]
  })

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
    this.roleForm.patchValue({
      role: account.role
    })
   
  }

  onSubmit(){
    if(this.editForm.value.password == ''){
        this.editForm.value.password= this.account.password;
    }
    let newaccount = this.editForm.value;
    let idAccount = this.route.snapshot.paramMap.get('id_account') || '';
    console.log(newaccount)
    let newbalance = this.balForm.value.balance || 0;
    if(newbalance > 0){
      this.accountService.addBalance(newbalance,idAccount).subscribe(()=>this.accountService.editAccount(newaccount,idAccount).subscribe(() => {this.checkChangeRole();}));

    }
    else{this.accountService.editAccount(newaccount,idAccount).subscribe(() => {
      this.checkChangeRole();
      });}
   
  }
  checkChangeRole() {
    let newrole = this.roleForm.value.role || '';
    console.log(newrole);

    if(newrole !== this.account.role){
      let idAccount = this.route.snapshot.paramMap.get('id_account') || '';
      console.log("PAtch role")
      this.accountService.changeRole(newrole,idAccount).subscribe(()=>this.location.back());
    }
    else{
      this.location.back()
    }
  }

}
