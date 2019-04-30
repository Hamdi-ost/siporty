import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { first } from 'rxjs/operators';

import { User } from '../../models';
import { UserService, AuthenticationService, AlertService } from '../../services';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import * as $ from 'jquery';

@Component({ selector: 'app-profile', styleUrls: ['./profile.component.css'], templateUrl: 'profile.component.html' })
export class ProfileComponent implements OnInit, OnDestroy {
  currentUser: User;
  currentUserSubscription: Subscription;
  users: User[] = [];
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService
  ) {
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
      if (user) {
        this.currentUser = user['user'];
      }
      console.log(this.currentUser);
      console.log(user);
    });
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
