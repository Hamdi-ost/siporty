import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  
  title = "users";
  users = [
    {id: 1, username: 'hamdi', email: 'hamdi@gmail.com', revenue: 10},
    {id: 2, username: 'hamza', email: 'hamza@gmail.com', revenue: 10}
  ];
  columnsName = ['username', 'email', 'revenue'];

  constructor(private userService: UserService) { }

  ngOnInit() {
    // this.userService.getAll().subscribe(users => {
    //   this.users.push(users);
    // })
  }

  banUser(id) {
    // this.userService.ban(id).subscribe();
  }

}
