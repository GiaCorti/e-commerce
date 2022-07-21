import { Component, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/api';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  items: MenuItem[] = [];
  isAdmin = false;
  isLogged:boolean | undefined;
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.isLogged.subscribe(res => this.isLogged = res);
    //this.isLogged = this.authService.hasValidAccessToken();
    this.authService.isAdmin().subscribe(res => {this.isAdmin = res;
     
    if(this.isAdmin){
    this.items = [
      {
        icon: 'pi pi-user',
          items: [
              {label: 'Detail', url:'account/detail/dada', icon:'pi pi-id-card'},
              {label: 'Modify', url:'account/modify/dada',  icon:'pi pi-user-edit'},
              {label: 'Accounts',  icon:'pi pi-users'},
              {label: 'Logout',  icon:'pi pi-sign-out'}
          ]
  }
    ]}
    else{

      this.items = [
        {
          icon: 'pi pi-user',
            items: [
                {label: 'Detail', url:'account/detail/dada', icon:'pi pi-id-card'},
                {label: 'Modify', url:'account/modify/dada',  icon:'pi pi-user-edit'},
                {label: 'Logout',  icon:'pi pi-sign-out'}
            ]
    }
      ]

    }})
  
  }
}
