import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  title = "Users";
  users = [
    // {id: 1, username: 'hamdi', email: 'hamdi@gmail.com', revenue: 10, enable: true},
    // {id: 2, username: 'hamza', email: 'hamza@gmail.com', revenue: 10, enable: false}
  ];
  columnsName = ['firstname', 'username', 'email'];

  constructor(private userService: UserService) { }

  fetchData() {
    this.userService.getAllUsers().subscribe(users => {
      if (users) {
        this.users = Array.from(users).reverse();
      }
    })
  }

  ngOnInit() {
    this.fetchData();
  }

  banUser(id) {
    this.userService.ban(id).subscribe(data => {
      this.fetchData();
    });
  }

  unbanUser(id) {
    this.userService.unban(id).subscribe(data => {
      this.fetchData();
    });
  }

}
