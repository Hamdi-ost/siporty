import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-informations',
  templateUrl: './informations.component.html',
  styleUrls: ['./informations.component.css']
})
export class InformationsComponent implements OnInit {
  title = "Informations";
  infos = [
    { id: 1, username: 'hamdi', email: 'hamdi@gmail.com', card: '13121596', "income before": '12DT', "income after": '10DT' },
    { id: 2, username: 'hamza', email: 'hamza@gmail.com', card: '13121596', "income before": '10DT', "income after": '5DT' }
  ];
  columnsName = ['username', 'email', 'card', 'income before', 'income after'];

  constructor() { }

  ngOnInit() {
  }

}
