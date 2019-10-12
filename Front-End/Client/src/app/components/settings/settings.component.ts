import { DonationService } from './../../services/donation.service';
import { Component, OnInit } from '@angular/core';
import { UserService, AuthenticationService, AlertService } from 'src/app/services';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import * as $ from 'jquery';
import { mimeType } from './mime-type.validator';


@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  currentUser;
  imagePreview;
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
      socialLink: [this.currentUser.user.socialLink],
      image: new FormControl(null, { validators: [Validators.required], asyncValidators: [mimeType] })

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
      console.log(this.settingsForm.invalid);
      return;
    }

    if (this.settingsForm.value.password !== this.settingsForm.value.repassword) {
      this.alertService.error('Password not matched');

      return;
    }

    if (this.currentUser) {
      if (!this.f.password.value && !this.f.image.value ) {
        this.userUpdated = {
          id: this.currentUser.user.id,
          socialLink: this.f.socialLink.value
        };
        console.log(this.userUpdated);
      } else if (this.f.password.value && !this.f.image.value) {

        this.userUpdated = {
          id: this.currentUser.user.id,
          password: this.f.password.value,
          socialLink: this.f.socialLink.value
        };
      } else if (!this.f.password.value && this.f.image.value) {

          this.userUpdated = {
            id: this.currentUser.user.id,
            socialLink: this.f.socialLink.value,
            image: this.f.image.value.title

          };
        } else {
        this.userUpdated = {
          id: this.currentUser.user.id,
          password: this.f.password.value,
          socialLink: this.f.socialLink.value,
          image: this.f.image.value.title
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
                       //window.location.reload();
          },
          error => {
            this.alertService.error(error.message);
            this.loading = false;
          }
        );
    }
  }

  onImagePicked(event: Event) {
    const file = (event.target as HTMLInputElement).files[0];
    this.settingsForm.patchValue({ image: file }); // target a single controle wich is in our case the image
    this.settingsForm.get('image').updateValueAndValidity();
    // convert imageto data url that can be used by img tag in html
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(file);
    console.log(file);     console.log("here");


  }




}
