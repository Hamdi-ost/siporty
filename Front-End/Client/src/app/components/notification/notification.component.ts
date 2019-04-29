import { DonationService } from './../../services/donation.service';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss']
})
export class NotificationComponent implements OnInit {

  user;

  constructor(private userAuth: AuthenticationService, private donationService: DonationService) {

  }

  notifications = [];

  ngOnInit() {
    this.user = this.userAuth.currentUser.subscribe(data =>
      this.donationService.getAllDonationDetails(data['user'].id)
        .subscribe(donation => {
          this.notifications = Array.from(donation['donationMessages']);
        }));

  }

}
