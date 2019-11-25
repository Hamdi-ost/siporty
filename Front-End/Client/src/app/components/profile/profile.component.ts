import { Component, OnInit, OnDestroy, Output, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';

import { User } from '../../models';
import { UserService, AuthenticationService, AlertService } from '../../services';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import * as $ from 'jquery';
import { DonationService } from 'src/app/services/donation.service';
import { GiffyComponent } from '../giffy/giffy.component';
import { EventEmitterService } from 'src/app/services/event-emitter.service';
import { EventEmitter } from 'events';

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
  TopDonorsPerMonth;
  TopDonorsPerWeek;

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private eventEmitterService: EventEmitterService,
    private donationService: DonationService,

  ) {
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
       // console.log(user);
      if (user) {
        this.currentUser = user['user'];
      }
    });
  }


  fetchTopFans() {
    console.log(this.currentUser);
    if (this.currentUser.id && this.currentUser) { //added
    this.donationService.getStatsById(this.currentUser.id, { date: this.todaysDate() }).subscribe(data => {
      this.TopDonorsPerMonth = data['topTenDonorsMonth'];
      this.TopDonorsPerWeek = data['topTenDonorsWeek'];
      });
    }
  }

  reformatDate(dateStr) {
    const dArr = dateStr.split('-');  // ex input "2010-01-18"
    return dArr[2] + '/' + dArr[1] + '/' + dArr[0].substring(); // ex out: "18/01/10"
  }

  ngOnDestroy() {
    // unsubscribe to ensure no memory leaks
    this.currentUserSubscription.unsubscribe();
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      accountName: [''],
      rib: [''],
      bankName: [''],
      agency: [''],
      phone: ['', Validators.required]
    });
    this.datePerMonth = this.todaysDate();
    this.fetchTopFans();
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
      phone: this.loginForm.value.phone,
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


  todaysDate() {
    const today = new Date();
    const dd = String(today.getDate()).padStart(2, '0');
    const mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    const yyyy = today.getFullYear();
      return dd + '/' + mm + '/' + yyyy;
  }


  firstComponentFunction() {
     this.eventEmitterService.onFirstComponentButtonClick();

  }

  launch() {
    this.eventEmitterService.launchMyFunction();
  }



}
