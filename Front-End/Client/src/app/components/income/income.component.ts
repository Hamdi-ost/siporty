import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-income',
  templateUrl: './income.component.html',
  styleUrls: ['./income.component.scss']
})
export class IncomeComponent implements OnInit {
  incomes = [
    { month: 'juin', amount: 11 },
    { month: 'may', amount: 50 },
    { month: 'july', amount: 30 }
  ];

  constructor() {

  }

  ngOnInit() {
  }

}
