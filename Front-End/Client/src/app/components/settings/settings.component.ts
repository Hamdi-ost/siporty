import { Component, OnInit } from '@angular/core';
import { UserService, AuthenticationService, AlertService } from 'src/app/services';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import * as $ from 'jquery';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  currentUser;
  url;
  settingsForm: FormGroup;
  submitted = false;
  returnUrl: string;
  loading = false;
  userUpdated;

  constructor(
    private formBuilder: FormBuilder,
    private alertService: AlertService,
    private router: Router,
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.authenticationService.currentUser.subscribe(user => {
      this.currentUser = user;
    });

    this.settingsForm = this.formBuilder.group({
      email: [this.currentUser.user.email, Validators.required],
      password: ['', Validators.required]
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.settingsForm.controls;
  }
  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.settingsForm.invalid) {
      return;
    }
    if (this.currentUser) {
      this.userUpdated = {
        id: this.currentUser.user.id,
        username: this.currentUser.user.username,
        firstname: this.currentUser.user.firstname,
        lastname: this.currentUser.user.lastname,
        enabled: this.currentUser.user.enabled,
        email: this.f.email.value,
        password: this.f.password.value,
        roles: ['ROLE_USER']
      };
    }
    if (this.userUpdated) {
      this.loading = true;
      this.userService
        .update(this.userUpdated)
        .subscribe(
          data => {
            this.router.navigate([this.returnUrl]);
            window.location.reload();
          },
          error => {
            this.alertService.error(error.message);
            this.loading = false;
          }
        );
    }
  }
}
