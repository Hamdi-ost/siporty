import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import * as $ from 'jquery';

import {
  AlertService,
  UserService,
  AuthenticationService
} from '../../services';
import { DonationService } from 'src/app/services/donation.service';

@Component({
  templateUrl: 'register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private alertService: AlertService,
    private donationDetails: DonationService
  ) {
    $(function () {
      $('.hide-show').show();
      $('.hide-show span').addClass('show');

      $('.hide-show span').click(function () {
        if ($(this).hasClass('show')) {
          $(this).text('Hide');
          $('input[name="login[password]"]').attr('type', 'text');
          $(this).removeClass('show');
        } else {
          $(this).text('Show');
          $('input[name="login[password]"]').attr('type', 'password');
          $(this).addClass('show');
        }
      });

      $('form button[type="submit"]').on('click', function () {
        $('.hide-show span').text('Show').addClass('show');
        $('.hide-show').parent().find('input[name="login[password]"]').attr('type', 'password');
      });
    });
    // redirect to home if already logged in
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      role: [['ROLE_USER']]
    });
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.registerForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }


    this.loading = true;
    this.userService
      .register(this.registerForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('Registration successful an email was sent to your email address', true);
          this.userService.registerValidate(data['id']);
          this.donationDetails.postDonationDetails({ id: data['id'], socialLink: null }).subscribe();
          this.router.navigate(['/login']);
        },
        error => {
          this.alertService.error(error.error.message);
          this.loading = false;
        }
      );
  }
}
