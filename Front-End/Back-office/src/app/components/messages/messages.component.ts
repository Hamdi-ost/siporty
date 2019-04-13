import { Component, OnInit } from '@angular/core';
import { ContactService } from 'src/app/services/contact.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  constructor(private messages: ContactService) { }

  ngOnInit() {
    this.messages.getAllMessages().subscribe(messages => {
      console.log(messages);
    })
  }

}
