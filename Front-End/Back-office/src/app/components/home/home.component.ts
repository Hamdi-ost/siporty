import { StatsService } from './../../services/stats.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  latestMembers = [];
  top10Donors = [];
  donationRate;
  visitRate;
  totalUsers;
  totalDonations;

  constructor(private statsService: StatsService) { }

  ngOnInit() {
    this.statsService.getAllStats().subscribe(stats => {
      console.log(stats);
      this.totalUsers = stats['totalUsers'];
      this.top10Donors = stats['topTenDonors'];
      this.totalDonations = stats['toptalDonors'];
    })
  }

}
