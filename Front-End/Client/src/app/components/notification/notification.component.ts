import { DonationService } from './../../services/donation.service';
import { Component, OnInit, Output } from '@angular/core';
import { AuthenticationService } from 'src/app/services';
import { EventEmitter } from 'events';
import { EventEmitterService } from 'src/app/services/event-emitter.service';


@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss']
})
export class NotificationComponent implements OnInit {

  user;
  p: number = 1;

  constructor(private userAuth: AuthenticationService, private donationService: DonationService,
              private eventService: EventEmitterService
    ) {

  }

  notifications = [];

  @Output() arraysizeEvent = new EventEmitter<number>();

  ngOnInit() {
    this.user = this.userAuth.currentUser.subscribe(data => {
      if (data) {
        this.donationService.getAllDonationDetails(data['user'].id)
          .subscribe(donation => {
            console.log(donation);
            this.notifications = Array.from(donation['donationMessages']).reverse();
          });
      }
    }
    );

  }

  // sendSize() {

  //   this.user = this.userAuth.currentUser.subscribe(data => {
  //     if (data) {
  //       this.donationService.getAllDonationDetails(data['user'].id)
  //         .subscribe(donation => {
  //           console.log(donation);
  //           this.notifications = Array.from(donation['donationMessages']).reverse();
  //         });
  //     }
  //   }
  //   );

  //     this.arraysizeEvent.emit(this.notifications.length);
  // }



onGetlength() {

  this.user = this.userAuth.currentUser.subscribe(data => {
    if (data) {
      this.donationService.getAllDonationDetails(data['user'].id)
        .subscribe(donation => {
          console.log(donation);
          this.notifications = Array.from(donation['donationMessages']).reverse();
        });
    }
  }
  );

      this.eventService.triggerArray.next(this.notifications.length);
}




}
