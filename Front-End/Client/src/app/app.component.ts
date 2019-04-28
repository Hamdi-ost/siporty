import { Component, AfterViewChecked } from '@angular/core';
import { AuthenticationService } from './services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewChecked {
  title = 'JEISITE';
  logIn = false;

  constructor(private authService: AuthenticationService, private router: Router) { }

  ngAfterViewChecked() {
    setTimeout(() => {
      if (JSON.parse(localStorage.getItem('currentUser'))) {
        this.logIn = true;
      } else {
        this.logIn = false;
      }
    });
  }

  logout() {

    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
