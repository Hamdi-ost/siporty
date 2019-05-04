import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ExcelService } from 'src/app/services/excel.service';

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
  @Output() reset = new EventEmitter();
  p = 1;

  constructor(private excelService: ExcelService) { }

  ngOnInit() {
  }

  banUser(id) {
    this.ban.emit(id);
  }

  unbanUser(id) {
    this.unban.emit(id);
  }

  exportAsXLSX(): void {
    this.excelService.exportAsExcelFile(this.data, 'sample');
  }

  payed(username) {
    const info = this.data.filter(el => el.username === username);
    this.reset.emit(info);
  }

}
