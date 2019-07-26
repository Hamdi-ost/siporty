import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AlertService, UserService, AuthenticationService } from 'src/app/services';
import { ContactService } from 'src/app/services/contact.service';
import { DonationService } from 'src/app/services/donation.service';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models';
import { PaymeeService } from 'src/app/services/paymee.service';

@Component({
  selector: 'app-donation',
  templateUrl: './donation.component.html',
  styleUrls: ['./donation.component.css']
})
export class DonationComponent implements OnInit {
  donationForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  id;
  username;
  socialLink;
  currentUserSubscription: Subscription;
  TopDonorsPerMonth;
  TopDonorsPerWeek;
  currentUser: User;
  datePerMonth: string;

  paymeeConfig = {};
  init_token: any = {token: ""};

  constructor(private formBuilder: FormBuilder,
    private userService: UserService,
    private alertService: AlertService,
    private donationService: DonationService,
    private authenticationService: AuthenticationService,
    private route: ActivatedRoute,
    private paymeeService: PaymeeService) {
      this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
        if (user) {
          this.currentUser = user['user'];
        }
      });
    }

  ngOnInit() {

    this.route.params.subscribe(params => {
      // this.id = params['id'];
      this.username = params['username'];
      this.userService.getUserByUsername(this.username).subscribe(data => {
        this.socialLink = data['socialLink'];
        this.id = data['id'];

        this.donationForm = this.formBuilder.group({
          id: this.id,
          name: ['', Validators.required],
          montant: ['', Validators.required],
          message: ['', Validators.required]
        });
      });
    });

    this.fetchTopFans();

  }

  changeMsg() {
    this.paymeeConfig['note'] = this.donationForm.value.message;
  }

  initPaymee() {

    this.paymeeConfig = {
      vendor: 1381,
      amount : 10.0,
      note : "string"
    }
    this.paymeeConfig['amount'] = this.donationForm.value.montant;
    console.log(this.paymeeConfig);

    this.paymeeService.initiate(this.paymeeConfig).subscribe(
      data => {
        console.log(data);
        if (data) {
          console.log(data);
          this.init_token = data;

          /*let value = {
            payment_token: this.init_token,
            url_ok: 'localhost:8080/donationsucceeded',
            url_ko : 'localhost:8080/donationsucceeded'
          };

          this.paymeeService.startPayment(value).subscribe(
              data => {
              if(data) {
                console.log(data);
                this.paymeeService.verifyPayment(this.init_token).subscribe(
                    data => {
                    if (data) {
                      console.log(data);
                    }
                  }
                )
              }
            }
          );*/
        }
      },
      error => {
        console.log(error);
      }
    )
  }

  // convenience getter for easy access to form fields
  get f() {

    return this.donationForm.controls;
  }

  fetchTopFans() {
    this.donationService.getStatsById(this.currentUser.id, { date: this.todaysDate() }).subscribe(data => {
      this.TopDonorsPerMonth = data['topTenDonorsMonth'];

      this.TopDonorsPerWeek = data['topTenDonorsWeek'];
      });
  }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.donationForm.invalid) {
      return;
    }

    this.loading = true;
    this.donationService
      .postDonation(this.donationForm.value)
      .subscribe(
        data => {
          this.alertService.success('Your Donation sent');
          window.location.href = '/donationsucceeded/' + this.username ;
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        }
      );
  }

  todaysDate() {
    const today = new Date();
    const dd = String(today.getDate()).padStart(2, '0');
    const mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    const yyyy = today.getFullYear();
      return dd + '/' + mm + '/' + yyyy;
  }
}
