import { Component, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/api';
import { AuthService } from '../services/auth.service';
import { Location } from '@angular/common';

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
    private location: Location) { }

  ngOnInit(): void {
    
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
            icon: 'pi pi-user',
              items: [
                  {label: 'Detail', routerLink:['/account/detail/'+this.userid], icon:'pi pi-id-card'},
                  {label: 'Modify', routerLink:['/account/modify/'+this.userid],  icon:'pi pi-user-edit'},
                  {label: 'Logout',  icon:'pi pi-sign-out',  command: () => this.logOut()}
              ]
      }
        ]
  
      }}
    

  logOut(){
    this.authService.logout();
    
    window.location.replace("/home")
  }
}


