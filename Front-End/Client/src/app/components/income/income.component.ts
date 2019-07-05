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
  incomes = [];

  constructor(private donationService: DonationService, private authenticationService: AuthenticationService) {

  }

  ngOnInit() {
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
      if (user) {
        this.currentUser = user['user'];

      }

        this.donationService.getDonationsByYear(this.currentUser.id, { date: this.todaysDate() }).subscribe(data => {
          console.log(data);
          //this.incomes = data["incomethisMonth"]; //does not work because we need an array of object

        });
    });

  }



  fetchTopFans() {
    console.log(this.todaysDate());
    this.donationService.getStatsById(this.currentUser.id, { date: this.reformatDate(this.todaysDate()) }).subscribe(data => {
    this.incomes = data['incomeThisMonth'];
    console.log(data);
    });
  }


  todaysDate() {
    const today = new Date();
    const dd = String(today.getDate()).padStart(2, '0');
    const mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    const yyyy = today.getFullYear();
      return dd + '/' + mm + '/' + yyyy;
  }

  reformatDate(dateStr) {
    const dArr = dateStr.split('-');  // ex input "2010-01-18"
    return dArr[2] + '/' + dArr[1] + '/' + dArr[0].substring(); // ex out: "18/01/10"
  }




}
