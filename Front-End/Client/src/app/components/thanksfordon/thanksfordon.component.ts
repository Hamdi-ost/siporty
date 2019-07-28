import { Component, OnInit , Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PaymeeService } from 'src/app/services/paymee.service';



@Component({
  selector: 'app-thanksfordon',
  templateUrl: './thanksfordon.component.html',
  styleUrls: ['./thanksfordon.component.scss']
})
export class ThanksfordonComponent implements OnInit {

  username;
  token = '';
  init_token: any = {token: '' };
 url = '';
 i;

  constructor(private route: ActivatedRoute, private paymeeService: PaymeeService, public activatedRoute: ActivatedRoute) {
   }

  ngOnInit() {




    this.route.params.subscribe(params => {
      this.username = params['username'];
 });

               this.paymeeService.verifyPayment(this.getToken()).subscribe(
                    data => {
                    if (data) {
                      console.log(data['result']);
                        if (data['result'] === 0) {
                          window.location.href = 'http://localhost:4200/donationfailed/';
                        }

                    }
                  }
               );
  }

 getToken() {
  this.i = 0;
    this.url = window.location.href ;
      for ( this.i = this.url.indexOf('=') + 1 ; this.i < this.url.indexOf('=') + 33 ; this.i++) {

    this.token = this.token + this.url[this.i];
}
this.init_token['token'] = this.token;

      return this.init_token;
}





}
