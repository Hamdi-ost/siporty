import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-thanksfordon',
  templateUrl: './thanksfordon.component.html',
  styleUrls: ['./thanksfordon.component.scss']
})
export class ThanksfordonComponent implements OnInit {

  username;
  constructor(private route: ActivatedRoute ) {
   }

  ngOnInit() {

    this.route.params.subscribe(params => {
      this.username = params['username'];
 });
  }

}
