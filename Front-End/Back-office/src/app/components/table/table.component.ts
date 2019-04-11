import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Title } from '@angular/platform-browser';


@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {
  
  @Input() title;
  @Input() data;
  @Input() columnsName;
  @Output() ban = new EventEmitter();
  @Output() unban = new EventEmitter();
  p = 1;
  constructor() { }

  ngOnInit() {
  }

  banUser(id) {
    this.ban.emit(id);
  }
  
  unbanUser(id) {
    this.unban.emit(id);
  }

}
