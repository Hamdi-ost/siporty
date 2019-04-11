import { Component, OnInit, OnChanges } from '@angular/core';
import { AuthenticationService } from './services/authentification.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnChanges {
  logIn = false;
  title = 'SIPORTY';

  constructor(private authService: AuthenticationService) {}

  ngOnInit(): void {
    // if (JSON.parse(localStorage.getItem('currentUser'))) {
    //   this.logIn = true;
    // } else {
    //   this.logIn = false;
    // }
    // console.log(this.logIn);  
  }

  ngOnChanges() {
  }
}
