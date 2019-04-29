import { DonationsService } from './../../services/donations.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-donations',
  templateUrl: './donations.component.html',
  styleUrls: ['./donations.component.css']
})
export class DonationsComponent implements OnInit {

  title = "Donations";
  donations = [];
  columnsName = ['Donor', 'Receiver', 'Amount', 'Date'];

  constructor(private donationService: DonationsService) { }

  ngOnInit() {
    this.donationService.getAllDonations()
      .subscribe(donations => {
        const don = [];
        don.push(donations);
        don[0].forEach(el => {
          if (el['donationMessages'].length > 0) {
            el['donationMessages'].forEach(element => {
              const donation = {
                Donor: element.name,
                Receiver: el['userInfo'].username,
                Amount: element.montant + ' DT',
                Date: element.date
              };
              this.donations.push(donation);
            });
          }
        });

      });
  }

}
