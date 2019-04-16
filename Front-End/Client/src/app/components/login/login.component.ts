import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import * as $ from 'jquery';
import { AlertService, AuthenticationService } from '../../services';

@Component({
  templateUrl: 'login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService
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
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
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

    this.loading = true;
    this.authenticationService
      .login(this.f.username.value, this.f.password.value)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate([this.returnUrl]);
          window.location.reload();
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        }
      );
  }
}
