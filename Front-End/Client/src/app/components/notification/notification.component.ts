import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss']
})
export class NotificationComponent implements OnInit {

  notifications = [
    { id: 1, username: 'hamdi 1', message: 'sa7a w far7a 1', amount: '10DT' },
    { id: 2, username: 'hamdi 2', message: 'sa7a w far7a 2', amount: '20DT' },
    { id: 3, username: 'hamdi 3', message: 'sa7a w far7a 3', amount: '30DT' }
  ];

  ngOnInit() {

  }

}
