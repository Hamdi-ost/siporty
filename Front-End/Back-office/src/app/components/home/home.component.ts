import { StatsService } from './../../services/stats.service';
import { Component, OnInit } from '@angular/core';
import { DonationsService } from 'src/app/services/donations.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  latestMembers = [];
  top10Donors = [];
  donationRate;
  visitRate = 100;
  totalUsers;
  totalDonations;
  incomethisweek;
  incomethimonth;


  constructor(private statsService: StatsService, private donationService: DonationsService) { }

  ngOnInit() {
    this.statsService.getAllStats().subscribe(stats => {
      this.totalUsers = stats['totalUsers'];
      this.top10Donors = stats['topTenDonors'];
      this.totalDonations = stats['totalDonors'];
      this.incomethisweek = stats['incomeThisWeek'];
      this.incomethimonth = stats['incomeThisMonth'];
      this.donationRate = this.incomethimonth;
    })


  }

  changeMonth(){
      this.donationRate = this.incomethimonth;
  }

  changeWeek(){
    this.donationRate = this.incomethisweek;

  }

}
