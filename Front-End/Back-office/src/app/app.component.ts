import { Component, OnInit, OnChanges, AfterViewChecked } from '@angular/core';
import { AuthenticationService } from './services/authentification.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewChecked {
  logIn = false;
  title = 'SIPORTY';

  constructor(private authService: AuthenticationService) { }

  ngAfterViewChecked() {
    setTimeout(() => {
      if (JSON.parse(localStorage.getItem('currentUser'))) {
        this.logIn = true;
      } else {
        this.logIn = false;
      }
    });
  }
  ngOnInit(): void {

  }

}
