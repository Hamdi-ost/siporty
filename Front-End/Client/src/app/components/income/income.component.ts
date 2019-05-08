import { Component, OnInit } from '@angular/core';
import { DonationService } from 'src/app/services/donation.service';
import { UserService, AuthenticationService } from 'src/app/services';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-income',
  templateUrl: './income.component.html',
  styleUrls: ['./income.component.scss']
})
export class IncomeComponent implements OnInit {
  currentUserSubscription: Subscription;
  currentUser;
  incomes = [
    { month: 'juin', amount: 11 },
    { month: 'may', amount: 50 },
    { month: 'july', amount: 30 }
  ];

  constructor(private donationService: DonationService, private authenticationService: AuthenticationService) {

  }

  ngOnInit() {
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
      if (user) {
        this.currentUser = user['user'];
      }
      console.log(new Date());
      this.donationService.getStatsById(this.currentUser.id, { date: this.reformatDate(new Date()) }).subscribe(data => {
        console.log(data);
      });
    });
  }

  reformatDate(dateStr) {
    const dArr = dateStr.split('-');  // ex input "2010-01-18"
    return dArr[2] + '/' + dArr[1] + '/' + dArr[0].substring(); // ex out: "18/01/10"
  }

}
