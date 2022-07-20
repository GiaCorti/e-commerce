import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
    private authService: AuthService) { }

  ngOnInit(): void {
  }

  loginForm = this.formBuilder.group({
    email: ['',Validators.requiredTrue],
    password: ['',Validators.required]
  });

  onLogin(){
    let password = this.loginForm.value.password;
    let email = this.loginForm.value.email;

    let passemail = email+":"+password;

    let enc = btoa(passemail);

   // this.authService.login(enc,email);
    
  }

}
