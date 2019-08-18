import { Component, OnInit } from '@angular/core';


@Component ({
  selector: 'app-donationfailed',
  templateUrl: './donationfailed.component.html',
  styleUrls: ['./donationfailed.component.scss']
})
export class DonationfailedComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    localStorage.removeItem('formSource');

  }

}
