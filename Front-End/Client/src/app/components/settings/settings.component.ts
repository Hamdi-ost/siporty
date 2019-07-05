import { DonationService } from './../../services/donation.service';
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
  selectedFile: File;
  image;

  constructor(
    private formBuilder: FormBuilder,
    private alertService: AlertService,
    private router: Router,
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private route: ActivatedRoute,
    private donationDetails: DonationService
  ) { }

  ngOnInit() {
    this.authenticationService.currentUser.subscribe(user => {
      this.currentUser = user;
    });

    this.settingsForm = this.formBuilder.group({
      email: [this.currentUser.user.email, Validators.required],
      password: ['', Validators.minLength(6)],
      repassword: [''],
      socialLink: [this.currentUser.user.socialLink]
    });


    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.settingsForm.controls;
  }

  onFileUpload(event){
    this.selectedFile = event.target.files[0];
    console.log(event);
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.settingsForm.invalid) {
      return;
    }

    if (this.settingsForm.value.password !== this.settingsForm.value.repassword) {
      this.alertService.error('Password not matched');
      return;
    }

    if (this.currentUser) {
      if (!this.f.password.value) {
        this.userUpdated = {
          id: this.currentUser.user.id,
          socialLink: this.f.socialLink.value
        };
        console.log(this.userUpdated);
      } else {
        this.userUpdated = {
          id: this.currentUser.user.id,
          password: this.f.password.value,
          socialLink: this.f.socialLink.value
        };
      }
    }

    if (this.userUpdated) {
      this.loading = true;
      this.userService
        .changePassword(this.userUpdated)
        .subscribe(
          data => {
            this.router.navigate([this.returnUrl]);
            //            window.location.reload();
          },
          error => {
            this.alertService.error(error.message);
            this.loading = false;
          }
        );
    }
  }
}
