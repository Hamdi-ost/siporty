import { Component, OnInit } from '@angular/core';
import { ContactService } from 'src/app/services/contact.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  title = 'Messages';
  messages = [];
  columnsName = ['name', 'email', 'content'];
  p = 1;

  constructor(private message: ContactService) { }

  ngOnInit() {
    this.message.getAllMessages().subscribe(messages => {
      if (messages) {
        this.messages = Array.from(messages).reverse();
      }
    })
  }

}
