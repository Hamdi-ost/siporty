import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-informations',
  templateUrl: './informations.component.html',
  styleUrls: ['./informations.component.css']
})
export class InformationsComponent implements OnInit {
  title = 'Informations';
  infos = [
    { id: 1, 'name account': 'hamdi', bank: 'UIB', agency: 'Tunis', rib: '13121596', amount: '10DT' },
    { id: 2, 'name account': 'hamza', bank: 'UIB', agency: 'Ariana', rib: '13121596', amount: '5DT' }
  ];
  columnsName = ['name account', 'bank', 'agency', 'rib', 'amount'];

  constructor() { }

  ngOnInit() {
  }

}
