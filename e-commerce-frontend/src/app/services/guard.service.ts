import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class GuardService implements CanActivate{

  constructor(private router: Router, private authService: AuthService) {}

  canActivate() {
    if (
      this.authService.hasValidAccessToken()
    ) {
      return true;
    } else {
      window.alert("You need to login to use this functionality")
      this.router.navigate(['/login']);
      return false;
    }
  }
}
