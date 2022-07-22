import { Component, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/api';
import { AuthService } from '../services/auth.service';
import { Location } from '@angular/common';
import { AccountService } from '../services/account.service';
import { ConfirmationService} from 'primeng/api';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  items: MenuItem[] = [];
  isAdmin = false;
  isLogged:boolean | undefined;
  userid: string = "oo";
  constructor(private authService: AuthService,
    private location: Location,
    private accountService: AccountService,
    private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.items = [
      {
        label: "Home",
        icon: 'pi pi-home',
        routerLink:['home']

      },
    {
      label: "Catalog",
      icon:'pi pi-shopping-bag',
      routerLink:['product/catalog']

    },
    {
      label: "login",
      icon:'pi pi-sign-in',
      routerLink:['login']

    }]
    
    this.authService.isLogged.subscribe(res => {this.isLogged = res
      this.authService.getUser().subscribe(re => {
        this.userid= re;
       })
       this.authService.isAdmin().subscribe(r => {
        console.log(r);
        this.isAdmin = r;
        this.setItem();})});
    //this.isLogged = this.authService.hasValidAccessToken();
    
  }

  setItem(){
    console.log("userid: ",this.userid)
    console.log("admin: ",this.isAdmin)
    if(this.isAdmin){
      this.items = [
        {
          label: "Home",
          icon: 'pi pi-home',
          routerLink:['home']
  
        },
      {
        label: "Catalog",
        icon:'pi pi-shopping-bag',
        routerLink:['product/catalog']
  
      },
      {
        label: "Cart",
        icon: 'pi pi-shopping-cart',
        routerLink:['cart']
      },
        {
          icon: 'pi pi-user',
            items: [
                {label: 'Detail', routerLink:['/account/detail/'+this.userid], icon:'pi pi-id-card'},
                {label: 'Modify', routerLink:['/account/modify/'+this.userid],  icon:'pi pi-user-edit'},
                {label: 'Accounts',routerLink:['/account/list'] , icon:'pi pi-users'},
                {label: 'Logout',  icon:'pi pi-sign-out', command: () => this.logOut()}
            ]
    }
      ]}
      else{
  
        this.items = [
          {
            label: "Home",
            icon: 'pi pi-home',
            routerLink:['home']
    
          },
        {
          label: "Catalog",
          icon:'pi pi-shopping-bag',
          routerLink:['product/catalog']
    
        },
        {
          label: "Cart",
          icon: 'pi pi-shopping-cart',
          routerLink:['cart']
        },
          {
            icon: 'pi pi-user',
              items: [
                  {label: 'Detail', routerLink:['/account/detail/'+this.userid], icon:'pi pi-id-card'},
                  {label: 'Modify', routerLink:['/account/modify/'+this.userid],  icon:'pi pi-user-edit'},
                  {label: 'Delete Account',icon:'pi pi-times',command: () => this.confirmDelete(this.userid) },
                  {label: 'Logout',  icon:'pi pi-sign-out',  command: () => this.logOut()}
              ]
      }
        ]
  
      }}
  delete(userid: string): void {
    this.accountService.delete(userid).subscribe(()=>this.logOut());
    
  }

  confirmDelete(userid: string): void {
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


