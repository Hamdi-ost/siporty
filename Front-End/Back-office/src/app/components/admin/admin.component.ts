import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  title = "Admin";
  admin = [
    {id: 1, name:'hamdii', username: 'hamdi', email: 'hamdi@gmail.com'},
    {id: 2, name:'hamzaa', username: 'hamza', email: 'hamza@gmail.com'}
  ];
  columnsName = ['name', 'username', 'email'];
  constructor() { }

  ngOnInit() {
  }

}
