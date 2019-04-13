import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentification.service';
import { User } from 'src/app/models';
import { ContactService } from 'src/app/services/contact.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  username;
  messages = [];

  constructor(private router: Router,
    private authenticationService: AuthenticationService,
    private messagesService: ContactService) {
    this.authenticationService.currentUser.subscribe(x => {
      if (x) {
        this.username = x['user'].username
      }
    });
  }

  fetchMessages() {
    this.messagesService.getAllMessages().subscribe(messages => {
      if (messages) {
        this.messages = Array.from(messages).reverse();
      }
    })

  }
  ngOnInit() {
    this.fetchMessages();
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
