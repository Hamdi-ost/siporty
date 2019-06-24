import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { first } from 'rxjs/operators';

import { User } from '../../models';
import { UserService, AuthenticationService, AlertService } from '../../services';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import * as $ from 'jquery';
import { DonationService } from 'src/app/services/donation.service';

@Component({ selector: 'app-profile', styleUrls: ['./profile.component.css'], templateUrl: 'profile.component.html' })
export class ProfileComponent implements OnInit, OnDestroy {
  currentUser: User;
  currentUserSubscription: Subscription;
  users: User[] = [];
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  datePerMonth = '';
  datePerWeek = '';
  date = '07/05/2019';
  TopDonors;

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private donationService: DonationService
  ) {
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
      if (user) {
        this.currentUser = user['user'];
      }
    });
  }


  fetchTopFans() {
    console.log(this.reformatDate(this.datePerMonth));
    this.donationService.getStatsById(this.currentUser.id, { date: this.reformatDate(this.datePerMonth) }).subscribe(data => {
      this.TopDonors = data['topTenDonorsMonth'];

      });
  }

  reformatDate(dateStr) {
    const dArr = dateStr.split('-');  // ex input "2010-01-18"
    return dArr[2] + '/' + dArr[1] + '/' + dArr[0].substring(); // ex out: "18/01/10"
  }

  onSearchChange(newValue) {
    this.datePerMonth = newValue;
    this.fetchTopFans();
  }
  ngOnDestroy() {
    // unsubscribe to ensure no memory leaks
    this.currentUserSubscription.unsubscribe();
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      accountName: ['', Validators.required],
      rib: ['', Validators.required],
      bankName: ['', Validators.required],
      agency: ['', Validators.required]
    });

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    const updatedUser = {
      id: this.currentUser.id,
      firstname: this.currentUser['firstname'],
      lastname: this.currentUser['lastname'],
      email: this.currentUser['email'],
      enabled: this.currentUser['enabled'],
      banque: this.loginForm.value.bankName,
      agence: this.loginForm.value.agency,
      ccb: this.loginForm.value.rib,
      username: this.currentUser.username,
      accountName: this.loginForm.value.accountName,
      roles: [
        'ROLE_USER'
      ]
    };

    this.loading = true;
    this.userService.update(updatedUser).subscribe(data => {
      this.alertService.success('Payout Method successful', true);
      //        window.location.reload();
      // this.loginForm.reset();
    });

  }
}
