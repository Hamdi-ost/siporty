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
  title = 'SIPORTY';
  logIn = false;
  url = '';
  chaine = '';
  arraySize;
  newArraySize;
  user;
  notifications = [];

  constructor(private authService: AuthenticationService, private router: Router,
    private userAuth: AuthenticationService, private donationService: DonationService,
    private eventService: EventEmitterService) { }

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
    this.userAuth.currentUser.subscribe((data:any) => {
      if (data) {
        this.user = data.user;
        this.donationService.getAllDonationDetails(data['user'].id).subscribe(donation => {
            this.notifications = Array.from(donation['donationMessages']).reverse();
            this.arraySize = this.notifications.length;
            //console.log(this.notifications);
           // this.checkLength();
        });
      }
    });

    /*this.eventService.triggerArray.subscribe(
        (lengthArray: number) => {
          if ( lengthArray > this.arraySize) {
            lengthArray  = this.arraySize;
            window.location.reload();
          }
        }
    );*/
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  /*receiveSize($event: any) {
    console.log(this.arraySize);
    this.arraySize = $event;
  }*/

  loadData() {
    this.donationService.getAllDonationDetails(this.user.id).subscribe(donation => {
        this.notifications = Array.from(donation['donationMessages']);
        this.newArraySize = this.notifications.length;
        //console.log(this.newArraySize);
    });
  }

  // checkLength() {
  //   interval(1000).subscribe(data => {
  //     //console.log(this.user.user.id)
  //     this.loadData();
  //     if(this.newArraySize > this.arraySize) {
  //       window.location.reload();
  //     }
  //   });
  // }

}

/*interval(500 * 60).subscribe(data => {
  this.ngOnInit()();
});*/
