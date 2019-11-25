import { Component, AfterViewChecked, OnInit } from '@angular/core';
import { AuthenticationService } from './services';
import { Router } from '@angular/router';
import { DonationService } from './services/donation.service';
import { EventEmitterService } from './services/event-emitter.service';
import { interval } from 'rxjs';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})



export class AppComponent implements AfterViewChecked, OnInit {
  title = 'JEISITE';
  logIn = false;
  url = '';
  chaine = '';
  arraySize;
  user;
  notifications = [];

  constructor(private authService: AuthenticationService, private router: Router,
    private userAuth: AuthenticationService, private donationService: DonationService,
    private eventService: EventEmitterService
    ) { }

  ngAfterViewChecked() {
    setTimeout(() => {
      if (JSON.parse(localStorage.getItem('currentUser'))) {
        this.logIn = true;
      } else {
        this.logIn = false;
      }
    });
  }



  ngOnInit() {

    this.url = window.location.pathname;

    this.user = this.userAuth.currentUser.subscribe(data => {
      if (data) {
        this.donationService.getAllDonationDetails(data['user'].id)
          .subscribe(donation => {
            this.notifications = Array.from(donation['donationMessages']).reverse();
            this.arraySize = this.notifications.length;
            console.log(this.arraySize);
          });
      }
    }
    );

      this.eventService.triggerArray.subscribe(
          (lengthArray: number) => {
          if ( lengthArray > this.arraySize) {
            lengthArray  = this.arraySize;
            window.location.reload();
          }

          }


      );

  }


  logout() {

    this.authService.logout();
    this.router.navigate(['/login']);
  }


  receiveSize($event: any) {
    console.log(this.arraySize);
    this.arraySize = $event;

  }





}

interval(500 * 60).subscribe(data => {
  this.ngOnInit()();
});
