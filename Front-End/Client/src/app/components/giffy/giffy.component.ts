import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-giffy',
  templateUrl: './giffy.component.html',
  styleUrls: ['./giffy.component.scss']
})
export class GiffyComponent implements OnInit {


  counter;
  intervalId;
  constructor() { }

  ngOnInit() {
    $('body').css('background-color', 'rgb(255, 255, 255)');
    document.getElementById('photo_equipe').style.display = 'none';
    this.counter = 5 ;
    this.intervalId = null;


  }

}
