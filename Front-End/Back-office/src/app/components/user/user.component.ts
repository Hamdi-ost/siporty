import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services';
import { ConfirmationDialogueService } from '../confirmation-dialogue/confirmation-dialogue.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  title = "Users";
  users = [];
  columnsName = ['firstname', 'username', 'email'];

  constructor(private userService: UserService, private confirmationDialogueService: ConfirmationDialogueService) { }

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
