import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  title = "Admin";
  admins = [];

  columnsName = ['firstname', 'username', 'email'];
  constructor(private userService: UserService) { 
    this.userService.getAllAdmins().subscribe(admins => {
      this.admins = Array.from(admins).reverse();      
    })
  }

  ngOnInit() {
  }

}
