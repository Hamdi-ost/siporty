import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-donations',
  templateUrl: './donations.component.html',
  styleUrls: ['./donations.component.css']
})
export class DonationsComponent implements OnInit {

  title = "Donations";
  donations = [
    {id: 1, Donor: 'hamdi', Receiver: 'Mo7ssen', Amount: 10},
    {id: 2, Donor: 'hamza', Receiver: 'Karima', Amount: 10}
  ];
  columnsName = ['Donor', 'Receiver', 'Amount'];

  constructor() { }

  ngOnInit() {
  }

}
