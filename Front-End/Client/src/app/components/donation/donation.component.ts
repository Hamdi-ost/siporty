import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AlertService, UserService, AuthenticationService } from 'src/app/services';
import { ContactService } from 'src/app/services/contact.service';
import { DonationService } from 'src/app/services/donation.service';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models';

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

  constructor(private formBuilder: FormBuilder,
    private userService: UserService,
    private alertService: AlertService,
    private donationService: DonationService,
    private authenticationService: AuthenticationService,
    private route: ActivatedRoute) {
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
          window.location.href = '/donationsucceeded';
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
