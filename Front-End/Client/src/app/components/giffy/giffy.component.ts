import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-giffy',
  templateUrl: './giffy.component.html',
  styleUrls: ['./giffy.component.scss']
})
export class GiffyComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    $('body').css('background-color', 'rgb(0, 154, 64)');
  }

}
