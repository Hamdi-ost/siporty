import { Component, OnInit , Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PaymeeService } from 'src/app/services/paymee.service';
import { DonationService } from 'src/app/services/donation.service';
import { AlertService } from 'src/app/services';



@Component({
  selector: 'app-thanksfordon',
  templateUrl: './thanksfordon.component.html',
  styleUrls: ['./thanksfordon.component.scss']
})
export class ThanksfordonComponent implements OnInit {

  username;
  formDonation;
  token = '';
  init_token: any = {token: '' };
 url = '';
 i;

  constructor(private route: ActivatedRoute,
    private donationService: DonationService,
    private alertService: AlertService,
    private paymeeService: PaymeeService, public activatedRoute: ActivatedRoute) {
   }




  ngOnInit() {

    this.route.params.subscribe(params => {
      this.username = params['username'];
 });

               this.paymeeService.verifyPayment(this.getToken()).subscribe(
                    data => {
                    if (data) {
                      if (data['result'] === 1 ) {
                      //console.log(JSON.parse(localStorage.getItem('formSource')));
                      this.donationService
                      .postDonation(JSON.parse(localStorage.getItem('formSource')))
                      .subscribe(
                        data => {
                          localStorage.removeItem('formSource');

                          //this.alertService.success('Your Donation sent');
                         // window.location.href = '/donationsucceeded/' + this.username ;

                        },
                        error => {
                         // this.alertService.error('Your donation has already been sent');

                        }
                      ); }

                        if (data['result'] === 0) {
                          window.location.href = 'http://siporty.tn/donationfailed/';
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
