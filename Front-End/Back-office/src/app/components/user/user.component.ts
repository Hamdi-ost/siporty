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
    this.confirmationDialogueService
      .confirm('Confirmer s\'il vous plait..', ' Vous etes sur de supprimer ce produit?')
      .then(confirmed => {
        console.log('User confirmed:', confirmed);
        if (confirmed) {
          this.userService.ban(id).subscribe(data => {
            this.fetchData();
          });
        }
      })
      .catch(() =>
        console.log(
          'User dismissed the dialog (e.g., by using ESC, clicking the cross icon, or clicking outside the dialog)'
        )
      );
  }

  unbanUser(id) {
    this.userService.unban(id).subscribe(data => {
      this.fetchData();
    });
  }


}
