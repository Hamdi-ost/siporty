import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { AuthenticationService } from '../services';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const url = window.location.toString().slice(window.location.toString().indexOf('%2') + 3);
    console.log(url);
    if (url === 'aboutUs' || url === 'contact' || url === 'donation%2Fsiporty' || url === 'tp://localhost:4200/contact' || url === 'tp://localhost:4200/donation/siporty' || url === 'tp://localhost:4200/aboutUs') {
      return true;
    }
    const currentUser = this.authenticationService.currentUserValue;
    if (currentUser) {
      // authorised so return true
      return true;
    }

    // not logged in so redirect to login page with the return url
    this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
    return false;
  }
}
